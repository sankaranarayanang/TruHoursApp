package com.cts.pw;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TimerApp implements Runnable, ActionListener {
	private JFrame f;
	private Thread t = null;
	/*
	 * private int hours = 0; int minutes = 0, seconds = 0;
	 */
	//private String currentTimeString = "", loggedInTimeString = "";
	private String loggedInTimeString = "", approxLogoutTimeString = "", currentTimeString = "";
	private JButton startTimerButton, currentTimeButton, loginTimeButton, approxLogoutTimeButton;
	private JLabel startTimerLabel, currentTimeLabel, loginTimeLabel, approxLogoutTimeLabel;

	private void createAndAddButtons(String loggedInTimeString, String approxLogoutTimeString) {
		/*
		currentTimeButton = new JButton();
		loginTimeButton = new JButton();
		approxLogoutTimeButton = new JButton();
		*/

		currentTimeLabel = new JLabel("Current Time");
		loginTimeLabel = new JLabel("Logged IN Time");
		approxLogoutTimeLabel = new JLabel("Approximate Logout Time");

		// f.add(currentTimeLabel);
		// f.add(currentTimeButton);
		addLabelToFrame(f, currentTimeLabel);
		addButtonToFrame(f, currentTimeButton);

		// f.add(loginTimeLabel);
		// f.add(loginTimeButton);
		addLabelToFrame(f, loginTimeLabel);
		addButtonToFrame(f, loginTimeButton);

		loginTimeButton.setText(loggedInTimeString);
		loginTimeButton.addActionListener(this);
		loginTimeButton.setActionCommand("loginTimeButton");
		

		// f.add(approxLogoutTimeLabel);
		// f.add(approxLogoutTimeButton);
		addLabelToFrame(f, approxLogoutTimeLabel);
		addButtonToFrame(f, approxLogoutTimeButton);

		approxLogoutTimeButton.setText(approxLogoutTimeString);
		approxLogoutTimeButton.addActionListener(this);
		approxLogoutTimeButton.setActionCommand("approxLogoutTimeButton");
		

	}

	TimerApp() {
		f = new JFrame();
		f.setLayout(new FlowLayout());
		t = new Thread(this);

		// t.start();

		startTimerButton = new JButton();
		startTimerButton.setText("Start");

		startTimerLabel = new JLabel();
		startTimerLabel.setText("Start the timing...");

		addLabelToFrame(f, startTimerLabel);
		addButtonToFrame(f, startTimerButton);
		// f.add(startTimerLabel);
		// f.add(startTimerButton);

		startTimerButton.addActionListener(this);
		startTimerButton.setActionCommand("startTimerButton");

		f.setSize(300, 400);
		f.setVisible(true);
		f.setTitle("TruHours App");
		
		currentTimeButton = new JButton();
		loginTimeButton = new JButton();
		approxLogoutTimeButton = new JButton();
	}

	private void addLabelToFrame(JFrame frame, JLabel label) {
		frame.add(label);
	}

	private void addButtonToFrame(JFrame frame, JButton button) {
		frame.add(button);
	}

	public void run() {
		try {
			while (true) {

				/*
				 * Calendar cal = Calendar.getInstance(); hours = cal.get(Calendar.HOUR_OF_DAY);
				 * if (hours > 12) hours -= 12;
				 */
				// minutes = cal.get(Calendar.MINUTE);
				// seconds = cal.get(Calendar.SECOND);

				/*
				 * SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss"); Date date =
				 * cal.getTime(); timeString = formatter.format(date);
				 */
				currentTimeString = DateUtil.formatDateAsString(DateUtil.getCurrentDateAndTime());
				System.out.println("currentTimeString is " + currentTimeString);
				//printTime();
				if(currentTimeString != null) {
					currentTimeButton.setText(currentTimeString);
				} else {
					currentTimeButton.setText(DateUtil.formatDateAsString(DateUtil.getCurrentDateAndTime()));
					System.out.println("ERROR : currentTimeString is null.");
				}
				
				Thread.sleep(1000); // interval given in milliseconds
				
				if(DateUtil.formatDateAsString(DateUtil.getCurrentDateAndTime()).equals(approxLogoutTimeString)){
					JOptionPane.showMessageDialog(f, "You have completed 9 hours for the day successfully!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
	public void printTime() {
		currentTimeButton.setText(currentTimeString);
	}
*/
	public static void main(String[] args) {
		new TimerApp();

	}

	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getActionCommand().equals("startTimerButton")) {
			int timerConfirmationResponse = JOptionPane.showConfirmDialog(f,
					"Are you sure you want to start the timer for the day ?");
			if (timerConfirmationResponse == JOptionPane.YES_OPTION) {
				startTimer();
			}
		}
	}

	private void startTimer() {

		JOptionPane.showMessageDialog(f, "Timer Started for the day!");
		t.start();
		startTimerButton.setText("Timer Started");
		startTimerLabel.setText("Timer is in progress...");
		startTimerButton.setEnabled(false);
		loggedInTimeString = DateUtil.formatDateAsString(DateUtil.getCurrentDateAndTime());
		//loggedInTimeString.split(arg0)
		//approxLogoutTimeString = DateUtil.formatDateAsString(new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR + 9, Calendar.MINUTE).getTime());
		String[] arrOfStr = loggedInTimeString.split(":"); 
		approxLogoutTimeString = DateUtil.formatDateAsString(new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR + 9, Integer.parseInt(arrOfStr[1])).getTime());
		createAndAddButtons(loggedInTimeString, approxLogoutTimeString);
	}

}