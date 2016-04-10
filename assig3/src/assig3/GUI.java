package assig3;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static GUI instance;
	private JButton currentTime = new JButton();
	private JButton server1[] = new JButton[5];
	private JButton server2[] = new JButton[5];
	private JButton server3[] = new JButton[5];
	private JButton server4[] = new JButton[5];
	private JButton server5[] = new JButton[5];

	public GUI() {
		this.setLayout(new GridLayout(1, 6));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panels[] = new JPanel[5];
		for (int i = 0; i < 5; i++) {
			server1[i] = new JButton();
			server2[i] = new JButton();
			server3[i] = new JButton();
			server4[i] = new JButton();
			server5[i] = new JButton();
			panels[i] = new JPanel();
			panels[i].setLayout(new GridLayout(5, 1));
			add(panels[i]);
		}
		for (int i = 0; i < 5; i++) {
			panels[0].add(server1[i]);
			panels[1].add(server2[i]);
			panels[2].add(server3[i]);
			panels[3].add(server4[i]);
			panels[4].add(server5[i]);
		}
		server1[0].setBackground(Color.WHITE);
		server1[0].setBorder(null);
		server1[0].setText("Checkout 1");

		server2[0].setBackground(Color.WHITE);
		server2[0].setBorder(null);
		server2[0].setText("Checkout 2");

		server3[0].setBackground(Color.WHITE);
		server3[0].setBorder(null);
		server3[0].setText("Checkout 3");

		server4[0].setBackground(Color.WHITE);
		server4[0].setBorder(null);
		server4[0].setText("Checkout 4");

		server5[0].setBackground(Color.WHITE);
		server5[0].setBorder(null);
		server5[0].setText("Checkout 5");

		server1[0].setBackground(Color.WHITE);
		server1[0].setBorder(null);
		server1[0].setText("Checkout 1");
		currentTime.setText("Haha");
		this.add(currentTime);
		this.setSize(1200, 400);
		this.setVisible(true);
	}

	public void addTask(Task t) {
		switch (t.getServerNr()) {
		case 0:
			int i = 1;
			while (i < 4 && server1[i].getText() != null && !server1[i].getText().isEmpty()) {
				i++;
			}
			server1[i].setText(t.toString());
			break;
		case 1:
			i = 1;
			while (i < 4 && server2[i].getText() != null && !server2[i].getText().isEmpty()) {
				i++;
			}
			server2[i].setText(t.toString());
			break;
		case 2:
			i = 1;
			while (i < 4 && server3[i].getText() != null && !server3[i].getText().isEmpty()) {
				i++;
			}
			server3[i].setText(t.toString());
			break;
		case 3:
			i = 1;
			while (i < 4 && server4[i].getText() != null && !server4[i].getText().isEmpty()) {
				i++;
			}
			server4[i].setText(t.toString());
			break;
		case 4:
			i = 1;
			while (i < 4 && server5[i].getText() != null && !server5[i].getText().isEmpty()) {
				i++;
			}
			server5[i].setText(t.toString());
			break;

		}
	}

	public void setTime(int time) {
		currentTime.setText(Integer.toString(time));
	}

	public static GUI getInstance() {
		if (instance == null) {
			synchronized (GUI.class) {
				if (instance == null) {
					instance = new GUI();
				}
			}
		}
		return instance;
	}

	public void pop(int nr) {
		switch (nr) {
		case 0:
			int i = 2;
			server1[1].setText("");
			while (i < 5 && server1[i].getText() != null && !server1[i].getText().isEmpty()) {
				server1[i - 1].setText(server1[i].getText());
				i++;
			}
				server1[i-1].setText("");
			break;
		case 1:
			i = 2;
			server2[1].setText("");
			while (i < 5 && server2[i].getText() != null && !server2[i].getText().isEmpty()) {
				server2[i - 1].setText(server2[i].getText());
				i++;
			}
				server2[i-1].setText("");
			break;
		case 2:
			i = 2;
			server3[1].setText("");
			while (i < 5 && server3[i].getText() != null && !server3[i].getText().isEmpty()) {
				server3[i - 1].setText(server3[i].getText());
				i++;
			}
				server3[i-1].setText("");
			break;
		case 3:
			i = 2;
			server4[1].setText("");
			while (i < 5 && server4[i].getText() != null && !server4[i].getText().isEmpty()) {
				server4[i - 1].setText(server4[i].getText());
				i++;
			}
				server4[i-1].setText("");
			break;
		case 4:
			i = 2;
			server5[1].setText("");
			while (i < 5 && server5[i].getText() != null && !server5[i].getText().isEmpty()) {
				server5[i - 1].setText(server5[i].getText());
				i++;
			}
				server5[i-1].setText("");
			break;
		}

	}
}
