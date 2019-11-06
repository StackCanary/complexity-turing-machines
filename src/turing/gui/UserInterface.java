package turing.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.Timer;

import turing.graph.Simulation;
import turing.machine.Machine;
import turing.machine.MachineFactory;

public class UserInterface extends JFrame {
	private static final long serialVersionUID = 1L;

	final int h = 800;
	final int w = 1200;

	JMenuBar   menu = new JMenuBar(); 
	JButton    rset = new JButton("Reset");
	JButton    step = new JButton("Step");
	JButton    gooo = new JButton("Go!");

	boolean going = false;

	public UserInterface(Machine machine)
	{
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(w, h));

		JTextField inpt = new JTextField();

		Simulation simulation = new Simulation(800, h, machine.states);

		setJMenuBar(menu);

		menu.add(step);
		menu.add(gooo);
		menu.add(rset);
		menu.add(inpt);

		setLayout(new GridLayout(1, 2));

		add(new StatePanel(machine, simulation, w, h));
		add(new TTapePanel(machine));

		ActionListener listener = new ActionListener() 
		{
			boolean complete = false;
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (complete)
					((Timer)e.getSource()).stop();
				complete = simulation.step();
				repaint(); revalidate();
			}
		};

		ActionListener goListener = new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (going)
				{
					going = machine.step();
					repaint(); revalidate();
				}
			}
		};

		step.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				machine.step();
				repaint();
			}
		});


		rset.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				going = false;
				machine.reset(inpt.getText().trim());
				repaint();
			}
		});

		Timer timer = new Timer(1, listener);
		timer.start();


		gooo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				going = true;
			}
		});

		Timer goTimer = new Timer(50, goListener);
		goTimer.start();

		pack();
		setVisible(true);
	}

	public static void main(String... strings) throws IOException
	{

		JFrame jframe = new JFrame();

		JFileChooser chooser = new JFileChooser();

		if (chooser.showOpenDialog(jframe) == JFileChooser.APPROVE_OPTION) 
		{
			Machine machine = MachineFactory.make(chooser.getSelectedFile());
			machine.reset("");
			UserInterface ui = new UserInterface(machine);

		} else {
			System.out.println("Please select a file from the machines folder.");
		}

	}

}
