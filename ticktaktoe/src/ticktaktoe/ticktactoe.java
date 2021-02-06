// Author: Aadarsh Mehdi
//Logic by Aadarsh Mehdi


package ticktaktoe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;
public class ticktactoe extends JFrame implements ActionListener{
	private JPanel main;
	private JPanel gamepanel;
	private JPanel statuspane;
	private JButton button[][];
	private JLabel score;
	private JLabel status;
	private int oscore;
	private int xscore;
	private int totalgames;
	private boolean decision;
	private boolean turn;
	private boolean checkvar[][];
	private boolean checkos[][];
	private boolean gameover;
	private JButton reset;
	private static int counter;
	public ticktactoe() {
		turn=false;
		checkvar=new boolean[3][3];
		checkos=new boolean[3][3];
		gameover=false;
		counter=0;
		init ();
		
		
	}
	public void init() {
		setTitle("TICK TAC TOE");
		main=new JPanel(new BorderLayout());
		gamepanel=new JPanel(new GridLayout(3,3));
		statuspane=new JPanel(new GridLayout (1,3));
		button=new JButton[3][3];
		for (int i =0; i<button.length; i++) {
			for (int j=0; j<button[i].length;j++ ) {
				
				button[i][j]=new JButton();
				button[i][j].setForeground(Color.red);
				button[i][j].setText("_");
				button[i][j].setBackground(Color.black);
				button[i][j].setFont(new Font("Calibri", Font.BOLD,50));
				button[i][j].addActionListener(this);
				checkvar[i][j]=false;
				checkos[i][j]=false;
				gamepanel.add(button[i][j]);
			}
		}
		
		score=new JLabel(String.format("Score: O= %d  |  X= %d", getOScore(),getXScore()));
		status=new JLabel("");
		status.setFont(new Font("Ariel",Font.BOLD,25));
		status.setForeground(Color.RED);

		reset=new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent es){
				Reset();
			}
		});
		statuspane.add(score);
		statuspane.add(status);
		statuspane.add(reset);
		
		add(main);
		main.add(gamepanel, BorderLayout.CENTER);
		main.add(statuspane, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		counter++;
		JButton sign=(JButton)e.getSource();
		sign.setForeground(Color.red);
		
		if (turn==false && sign.getText()=="_") {
			sign.setText("X");
			sign.setEnabled(false);
			for (int i = 0; i < button.length; i++) {
				for (int j = 0; j < button[i].length; j++) {
					if (sign.equals(button[i][j])) {
						//ticks
						checkvar[i][j]=true;
					}
				}
			}
			turn=true;
			
		}
		else if (turn ==true && sign.getText()=="_") {
			sign.setText("O");
			sign.setEnabled(false);
			for (int i = 0; i < button.length; i++) {
				for (int j = 0; j < button[i].length; j++) {
					if (sign.equals(button[i][j])) {
						//ticks
						checkos[i][j]=true;
					}
				}
			}
			turn=false;
		}	
		checktick();
		checkcross();
		if (counter>8) {
			if(checkdraw()) {
				status.setText("Draw!");
				status.setVisible(true);
			}
		}
		score.setText(String.format("Score: O= %d  |  X= %d", getOScore(),getXScore()));
	}
	public boolean checktick() {
		
		//try {
		for (int j = 0; j < 3; j++) {
			//condition a
			decision=false;
			if ( checkos[1][1]&&checkos[2][2]&&checkos[0][0]) {
				
					decision=true;
					break;
			}
			
			//Horizontally
			else if (checkos[j][0]&&checkos[j][1]&&checkos[j][2]) {
				decision=true;
				break;
			}
			//Vertically
			else if (checkos[0][j]&&checkos[1][j]&&checkos[2][j]) {
				decision=true;
				break;
			}
			//Diagonal
			else if (checkos[0][2]&&checkos[1][1]&&checkos[2][0]) {
				decision =true;
				break;
			}
			else {
				//System.out.print("You Lose");
				decision=false;
				continue;
			}
		}
		if (decision==true) {
			incrementOScore();
			status.setVisible(true);
			status.setText("O wins");
			for (int i = 0; i < button.length; i++) {
				for (int j = 0; j < button[i].length; j++) {
					button[i][j].setEnabled(false);					
				}
			}
			return decision;
	
		}
		decision =false;
		//} catch(Exception e) {
			
		//}
		return decision;
		
	}
	public int incrementOScore() {
		oscore=oscore+1;
		return oscore;
	}
	public int incrementXScore() {
		xscore=xscore+1;
		return xscore;
	}
	public void Reset() {
		decision=false;
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[i].length; j++) {
				button[i][j].setText("_");
				button[i][j].setEnabled(true);
				checkvar[i][j]=false;
				checkos[i][j]=false;
				status.setVisible(false);
				counter=0;
			}
		}
	}
	public boolean checkdraw() {
		
		if (!checkcross() && !checktick()) {

			return true; 
		}
		else {
			return false;
		}
	}
	public boolean checkcross() {
	
		//try {
		for (int j = 0; j < 3; j++) {
			//condition a
			decision=false;
			if ( checkvar[1][1]&&checkvar[2][2]&&checkvar[0][0]) {
				
					decision=true;
					break;
			}
			
			//Horizontally
			else if (checkvar[j][0]&&checkvar[j][1]&&checkvar[j][2]) {
				decision=true;
				break;
			}
			//Vertically
			else if (checkvar[0][j]&&checkvar[1][j]&&checkvar[2][j]) {
				decision=true;
				break;
			}
			//Diagonal
			else if (checkvar[0][2]&&checkvar[1][1]&&checkvar[2][0]) {
				decision =true;
				break;
			}
			else {
				//System.out.print("You Lose");
				decision=false;
				continue;
			}
		}
		if (decision==true) {
			xscore++;
			status.setText("X wins");;
			status.setVisible(true);
			for (int i = 0; i < button.length; i++) {
				for (int j = 0; j < button[i].length; j++) {
					button[i][j].setEnabled(false);					
				}
			}
			return decision;
		}
		decision =false;
		//} catch(Exception e) {
			
		//}
		return decision;
	}
	public int getXScore() {
		return xscore;
	}
	public int getOScore() {
		return oscore;
	}
	public static void main(String args[]) {
		ticktactoe game=new ticktactoe();
		game.setVisible(true);
		game.setBounds(100,100, 400, 500);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
