import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class VisApp implements ActionListener {
	private JFrame appFrame;
	private VisPanel visPanel;
	
	public VisApp() {
		initialize();
		appFrame.setVisible(true);
	}
	
	private void initialize() {
		appFrame = new JFrame();
		appFrame.setTitle("A p p F r a m e | Version 0.1");
		appFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		appFrame.setBounds(100, 100, 1400, 700);
		
		initializePanel();
		initializeMenu();
	}
	
	private void initializeMenu() {
		JMenuBar menuBar = new JMenuBar();
		appFrame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		JMenuItem mi = new JMenuItem("Open CSV...", KeyEvent.VK_O);
		mi.addActionListener(this);
		mi.setActionCommand("open csv");
		mi.setEnabled(false);
		menu.add(mi);
		
		mi = new JMenuItem("Random Data", KeyEvent.VK_D);
		mi.addActionListener(this);
		mi.setActionCommand("random");
		menu.add(mi);
		
		menu.addSeparator();
		
		mi = new JMenuItem("Exit", KeyEvent.VK_X);
		mi.addActionListener(this);
		mi.setActionCommand("exit");
		menu.add(mi);
	}
	
	private void initializePanel() {
		visPanel = new VisPanel();
		visPanel.setBackground(Color.white);
		visPanel.setForeground(Color.darkGray);
		
		JPanel mainPanel = (JPanel)appFrame.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(visPanel, BorderLayout.CENTER);
	}
	
	public static void main (String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				VisApp app = new VisApp();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("random")) {
			// create some random data and populate the scatter plot panel
			Random rand = new Random();
			
			float xValues[] = new float[2000 + rand.nextInt(1000)];
			float yValues[] = new float[xValues.length];
			
			for (int i = 0; i < xValues.length; i++) {
				xValues[i] = rand.nextFloat() * 400.f;
				yValues[i] = rand.nextFloat() * 600.f;
			}
			
			visPanel.setData(xValues, yValues);
		} else if (event.getActionCommand().equals("exit")) {
			System.exit(0);
		}
	}
}
