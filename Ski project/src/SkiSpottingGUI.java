import java.awt.EventQueue;

import javax.swing.JFrame;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.Color;

public class SkiSpottingGUI {

	private JFrame frame;
	private JTextField textName;
	private JTextField textLastName;
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	private Graph graph;
	private User user;
	private Dijkstra dijkstra;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SkiSpottingGUI window = new SkiSpottingGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SkiSpottingGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 825, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("350px"),
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("400px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblName = new JLabel("Name");
		frame.getContentPane().add(lblName, "2, 2");
		
		JLabel lblResult = new JLabel("Result :");
		frame.getContentPane().add(lblResult, "4, 2");
		
		textName = new JTextField();
		frame.getContentPane().add(textName, "2, 4, left, default");
		textName.setColumns(10);
		
		JTextArea textResult = new JTextArea();
		textResult.setEditable(false);
		JScrollPane scroll = new JScrollPane(textResult);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scroll, "4, 4, 1, 25, fill, fill");
		
		JLabel lblLastname = new JLabel("Lastname");
		frame.getContentPane().add(lblLastname, "2, 6");
		
		textLastName = new JTextField();
		frame.getContentPane().add(textLastName, "2, 8, left, default");
		textLastName.setColumns(10);
		
		JLabel lblLevel = new JLabel("Level");
		frame.getContentPane().add(lblLevel, "2, 10");
		
		JComboBox<String>Level_list = new JComboBox<String>();
		Level_list.setModel(new DefaultComboBoxModel<String>(new String[] {"Beginner", "Intermediate", "Expert"}));
		frame.getContentPane().add(Level_list, "2, 12, fill, default");
		
		JLabel lblFrom = new JLabel("From");
		frame.getContentPane().add(lblFrom, "2, 14");
		
		JComboBox<String> From_list = new JComboBox<String>();
		frame.getContentPane().add(From_list, "2, 16, fill, default");
		
		JLabel lblTo = new JLabel("To");
		frame.getContentPane().add(lblTo, "2, 18");
		
		JComboBox<String> To_list = new JComboBox<String>();
		frame.getContentPane().add(To_list, "2, 20, fill, default");
		
		// Store the workspace path
		String workspacePath = System.getProperty("user.dir");
		
		// Store nodes from the CSV "nodes.csv" to an ArrayList (ReadCSVFile function
		// return an arraylist in which, each item is a line of the csv).
		ArrayList<String[]> CSVnodes = ReadCSV.ReadCSVFile(workspacePath + "\\src\\vertices.csv");

		// Declare an ArrayList to store the nodes read in the csv file
		nodes = new ArrayList<Node>();
		
		String[] listOfNodes = new String[CSVnodes.size()];

		// Create Nodes and store them in the ArrayList
		// The value is the newest Node which his constructor is Node(Id, Name,
		// Altitude).
		// Name correspond to the second column of the CSV
		// Altitude correspond to the third column of the CSV
		// We need to parse the ID and Altitude to Integer because the CSV return String
		for (int i = 0; i < CSVnodes.size(); i++) {
			int id = Integer.valueOf(CSVnodes.get(i)[0]);
			String name = CSVnodes.get(i)[1];
			int altitude = Integer.valueOf(CSVnodes.get(i)[2]);
			nodes.add(new Node(id, name,altitude));
			listOfNodes[i] = name;
			
		}
		From_list.setModel(new DefaultComboBoxModel<String>(listOfNodes));
		To_list.setModel(new DefaultComboBoxModel<String>(listOfNodes));
		
		JButton btnGo = new JButton("Go !");
		btnGo.setBackground(Color.LIGHT_GRAY);
		btnGo.setForeground(Color.BLACK);
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textResult.setText("Hello "+textName.getText()+" "+ textLastName.getText()+"\n"
						+ "Here is the proposed itinerary to go from the summit "+From_list.getSelectedItem().toString()
						+" to the summit "+To_list.getSelectedItem().toString()+" : \n");
				//Create the graph
				graph = new Graph(nodes, edges);
				
				//User IDs with his level
				user = new User(textName.getText(), textLastName.getText(), Level_list.getSelectedItem().toString());
				
				dijkstra = new Dijkstra(graph, user);
				
				//Define the source node (Id node - 1)
				dijkstra.execute(nodes.get(From_list.getSelectedIndex()));
				
				//Store the path from source to the destination node (Id node - 1)
				LinkedList<Node> path = dijkstra.getPath(nodes.get(To_list.getSelectedIndex()));
				
				//Store the edges covered by Dijkstra
				ArrayList<Edge> arcCoveredByPath = dijkstra.getArcsCovered();
				
				if (path == null) {
					System.out.println("Path does not exist");
					textResult.setText("Path does not exist \n");
				} else {
					textResult.setText(textResult.getText()+"List of summits through which you will pass : \n");
					for (Node node : path) {
						System.out.println(node.getId());
						textResult.setText(textResult.getText()+node.getId().toString()+"\n");
					}
					double time = 0.0;
					DecimalFormat df = new DecimalFormat(".00");
					textResult.setText(textResult.getText()+"List of ski slopes you need to use (name, type or difficulty, approx. time) : \n");
					for(Edge arc : arcCoveredByPath) {
						System.out.println(arc.getName() + " "+ arc.getTransportType() + " "+df.format(arc.getTime()));
						time+=arc.getTime();
						textResult.setText(textResult.getText()+arc.getName() + " "+ arc.getTransportType() + " "+df.format(arc.getTime())+"\n");
					}
					System.out.println("Total time = "+df.format(time)+ " minutes");
					textResult.setText(textResult.getText()+"Total time = "+df.format(time)+ " minutes \n");
				}
			}
		});
		frame.getContentPane().add(btnGo, "2, 26, 1, 3, fill, fill");
		// Store relations between each nodes from the CSV "data.csv" to an ArrayList
		ArrayList<String[]> csv_edge = ReadCSV.ReadCSVFile(workspacePath + "\\src\\relation_vertices.csv");
		
		// Declare an ArrayList to store the edges read in the csv file
		edges = new ArrayList<Edge>();
		
		//Populating the arcs ArrayList with the values of the CSV file (CSVarc ArrayList).
		for (int i = 0; i < csv_edge.size(); i++) {
			String name = csv_edge.get(i)[0];
			String typeTransport = csv_edge.get(i)[1];
			Node sourceNode = nodes.get(Integer.valueOf(csv_edge.get(i)[3]) - 1);
			Node destinationNode = nodes.get(Integer.valueOf(csv_edge.get(i)[2]) - 1);
			//If it's a Ski Lift
			if(typeTransport.startsWith("T"))
				edges.add(new SkiLift(name, sourceNode, destinationNode, typeTransport));
			else {
				//If it's a BUS
				if(typeTransport.equals("BUS")) 
					edges.add(new Bus(name, sourceNode, destinationNode));					
				//Else a Ski Slope
				else 
					edges.add(new SkiSlope(name, sourceNode, destinationNode, typeTransport));				
			}	
		}
		
		
		}
	}
