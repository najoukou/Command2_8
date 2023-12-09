package edu.uga.miage.m1.polygons.gui;
import java.util.logging.Logger;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.uga.miage.m1.polygons.gui.commands.Command;
import edu.uga.miage.m1.polygons.gui.commands.CommandHistory;
import edu.uga.miage.m1.polygons.gui.commands.DrawCommand;
import edu.uga.miage.m1.polygons.gui.commands.UndoCommand;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;

import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.Writer;
/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame implements MouseListener, MouseMotionListener {
	public boolean moveMode = false;
    private SimpleShape selectedShape;
    private static final Logger logger = Logger.getLogger(JDrawingFrame.class.getName());

	public enum Shapes {

        SQUARE, TRIANGLE, CIRCLE, CUBE
    }

    private static final long serialVersionUID = 1L;

    private JToolBar toolbar;

    private Shapes m_selected;
    private JPanel m_panel;
    private JLabel m_label;

    public List<SimpleShape> shapesList = new ArrayList<>();

    private ActionListener m_reusableActionListener = new ShapeActionListener();

    /**
     * Tracks buttons to manage the background.
     */
    private Map<Shapes, JButton> m_buttons = new HashMap<>();
    private CommandHistory history = new CommandHistory();

    /**
     * Default constructor that populates the main window.
     * @param frameName
     */
    public JDrawingFrame(String frameName) {
        super(frameName);
        // Instantiates components
        toolbar = new JToolBar("Toolbar");
        m_panel = new JPanel();
        m_panel.setBackground(Color.WHITE);
        m_panel.setLayout(null);
        m_panel.setMinimumSize(new Dimension(400, 400));
        m_panel.addMouseListener(this);
        m_panel.addMouseMotionListener(this);
        m_label = new JLabel(" ", JLabel.LEFT);
        // Fills the panel
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(m_panel, BorderLayout.CENTER);
        add(m_label, BorderLayout.SOUTH);
        // Add shapes in the menu
        addShape(Shapes.SQUARE, new ImageIcon(getClass().getResource("images/square.png")));
        addShape(Shapes.TRIANGLE, new ImageIcon(getClass().getResource("images/triangle.png")));
        addShape(Shapes.CIRCLE, new ImageIcon(getClass().getResource("images/circle.png")));
        addShape(Shapes.CUBE, new ImageIcon(getClass().getResource("images/underc.png")));
        setPreferredSize(new Dimension(400, 400));
       
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> undo());
        toolbar.add(undoButton);

        
        JButton exportXMLButton = new JButton("Export to XML");
        exportXMLButton.addActionListener(e -> {exportToXML("output.xml");});
        toolbar.add(exportXMLButton);

        JButton exportJSONButton = new JButton("Export to JSON");
        exportJSONButton.addActionListener(e -> {exportToJSON("output.json");});
        toolbar.add(exportJSONButton);
        
        JButton ImportJSONButton = new JButton("Import from JSON");
        ImportJSONButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a JSON file");
            fileChooser.setFileFilter(new FileNameExtensionFilter("JSON files", "json"));

            int userSelection = fileChooser.showOpenDialog(this); //

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                importFromJSON(selectedFile.getAbsolutePath());
            }
        });
        toolbar.add(ImportJSONButton);

    }

    private void importFromJSON(String filePath) {
    	try (JsonReader reader = Json.createReader(new FileReader(filePath))) {
            JsonObject jsonObject = reader.readObject();
            JsonArray shapesArray = jsonObject.getJsonArray("shapes");

            for (JsonObject shapeObject : shapesArray.getValuesAs(JsonObject.class)) {
                String type = shapeObject.getString("type");
                int x_shape = shapeObject.getInt("x");
                int y_shape = shapeObject.getInt("y");
                createShape(x_shape, y_shape, type);
	                
            }
        } catch (IOException e) {
        	logger.warning("An IOException occurred: " + e.getMessage());
            logger.warning("Stack trace: " + e);        }
	}

	public void undo(){
        if(!history.isEmpty()){
        	SimpleShape lastshape= this.history.pop().getShape();
        	Command undoCommand = new UndoCommand(lastshape, shapesList);
            undoCommand.execute();

            this.paintComponents(this.getGraphics());
        }
        }
    @Override
   public void paintComponents(Graphics g) {
        super.paintComponents(g);
        for (SimpleShape simpleShape : shapesList) {
            simpleShape.draw((Graphics2D) this.m_panel.getGraphics());
        }
    }

    public void exportToXML(String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
             String xmlWithHeader= "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                    "<root>\n" +
                    "<shapes>\n";
            for ( SimpleShape sh: shapesList) {
                XMLVisitor visitor = new XMLVisitor();
                    sh.accept(visitor);
                    String xmlContent = visitor.getRepresentation();
                    xmlWithHeader += xmlContent;
                    }
                    xmlWithHeader += "</shapes>\n</root>";
                    writer.write(xmlWithHeader);
            
        } catch (IOException e) {
        	logger.warning("An IOException occurred: " + e.getMessage());
            logger.warning("Stack trace: " + e);
            }
    }
     public void exportToJSON(String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            JsonArrayBuilder shapesArrayBuilder = Json.createArrayBuilder();
            for (SimpleShape sh: shapesList){
                JSonVisitor visitor = new JSonVisitor();
                    sh.accept(visitor);
                     String shapeJson = visitor.getRepresentation();
                    javax.json.JsonReader jsonReader = Json.createReader(new StringReader(shapeJson));
                    shapesArrayBuilder.add(jsonReader.readObject());
                    jsonReader.close();
                        }
                    
                JsonObjectBuilder jsonOutputBuilder = Json.createObjectBuilder()
                    .add("shapes", shapesArrayBuilder);
                javax.json.JsonWriter jsonWriter = Json.createWriter(writer);
                jsonWriter.writeObject(jsonOutputBuilder.build());
                jsonWriter.close();

        } catch (IOException e) {
        	logger.warning("An IOException occurred: " + e.getMessage());
            logger.warning("Stack trace: " + e);
        }		

            
    }
   
    public void addShapes(SimpleShape shape) {
        shapesList.add(shape);
    }


    @SuppressWarnings("serial")
	public class ShapePanel extends JPanel {
        private List<SimpleShape> shapes = new ArrayList<>();
    
        public void addShape(SimpleShape shape) {
            shapes.add(shape);
        }
    }

    
    
       
    
    public void addShape(Shapes shape, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        m_buttons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(m_reusableActionListener);
        if (m_selected == null) {
            button.doClick();
        }
        toolbar.add(button);
        toolbar.validate();
        repaint();
    }
     
    
    private void createShape(int x, int y,  String type ) {
    	SimpleShape shape= null;
    	ShapeFactory shapefactory = ShapeFactory.getInstance();

    	if ("circle".equals(type)) {
    	    shape = shapefactory.createShape(Shapes.CIRCLE, x, y);
    	} else if ("triangle".equals(type)) {
    	    shape = shapefactory.createShape(Shapes.TRIANGLE, x, y);
    	} else if ("square".equals(type)) {
    	    shape = shapefactory.createShape(Shapes.SQUARE, x, y);
    	} else if ("cube".equals(type)) {
    	    shape = shapefactory.createShape(Shapes.CUBE, x, y);
    	} else if (type == null) {
    	    shape = shapefactory.createShape(m_selected, x, y);
    	}
         if (shape != null) {
           Command drawCommand = new DrawCommand(shape, m_panel, shapesList );
             drawCommand.execute();
             history.push(drawCommand); 
    }}
   
    public void mouseClicked(MouseEvent evt) {
        if (m_panel.contains(evt.getX(), evt.getY())) {
            createShape(evt.getX(), evt.getY(), null);
        }
        
    }

  
    public void mouseEntered(MouseEvent evt) {
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
     */
    public void mouseExited(MouseEvent evt) {
        m_label.setText(" ");
        m_label.repaint();
    }

   
    public void mousePressed(MouseEvent evt) {
    	if (m_panel.contains(evt.getX(), evt.getY())) {
    		
           for (SimpleShape shape : shapesList) {
           	
               if (shape.isClicked(evt.getX(), evt.getY())) {
                   selectedShape = shape;
                   moveMode = true;
                   Command UndoCommand = new UndoCommand(selectedShape,shapesList);
          	  		UndoCommand.execute();
          	  		history.push(UndoCommand);

               this.paintComponents(this.getGraphics());

                   break;
               }
              
          }
     }
    }

    public void mouseDragged(MouseEvent evt) {
    	if (moveMode && selectedShape != null) {
    	  	selectedShape.setPosition(evt.getX(), evt.getY());
   	        			}
   
    }
    public void mouseReleased(MouseEvent evt) {
    	if (moveMode && selectedShape != null) {
            moveMode = false;
            Command DrawCommand = new DrawCommand(selectedShape, m_panel, shapesList);           
            DrawCommand.execute();
            history.push(DrawCommand);
        }
    }

    
    public void mouseMoved(MouseEvent evt) {
        modifyLabel(evt);
    }

    private void modifyLabel(MouseEvent evt) {
        m_label.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }

    
    private class ShapeActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            Iterator<Shapes> keys = m_buttons.keySet().iterator();
            while (keys.hasNext()) {
                Shapes shape = keys.next();
                JButton btn = m_buttons.get(shape);
                if (evt.getActionCommand().equals(shape.toString())) {
                    btn.setBorderPainted(true);
                    m_selected = shape;
                  
                    
                    
                    
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
       
    }

    public List<SimpleShape> getShapes() {
        return shapesList;
    }
}
