import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomePage1 {

    private JFrame frame;
    private DefaultListModel<Task> listModel;
    private JList<Task> taskList;
    private JTextField taskField;
    private JTextField dateField;
    private JTextField timeField;
    private JComboBox<String> priorityBox;

    public HomePage1() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("To-Do List");
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTasks = new JLabel("Your Tasks:");
        lblTasks.setBounds(30, 20, 100, 25);
        frame.getContentPane().add(lblTasks);

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setCellRenderer(new TaskRenderer());
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(30, 50, 420, 180);
        frame.getContentPane().add(scrollPane);

        taskField = new JTextField();
        taskField.setBounds(30, 240, 200, 25);
        taskField.setToolTipText("Enter task name");
        frame.getContentPane().add(taskField);

        dateField = new JTextField();
        dateField.setBounds(240, 240, 100, 25);
        dateField.setToolTipText("Date (YYYY-MM-DD)");
        frame.getContentPane().add(dateField);

        timeField = new JTextField();
        timeField.setBounds(350, 240, 100, 25);
        timeField.setToolTipText("Time (HH:MM)");
        frame.getContentPane().add(timeField);

        priorityBox = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        priorityBox.setBounds(30, 280, 100, 25);
        frame.getContentPane().add(priorityBox);

        JButton btnAdd = new JButton("Add Task");
        btnAdd.setBounds(150, 280, 120, 25);
        frame.getContentPane().add(btnAdd);

        JButton btnRemove = new JButton("Remove Task");
        btnRemove.setBounds(290, 280, 140, 25);
        frame.getContentPane().add(btnRemove);

        btnAdd.addActionListener(e -> {
            String name = taskField.getText().trim();
            String date = dateField.getText().trim();
            String time = timeField.getText().trim();
            String priority = (String) priorityBox.getSelectedItem();

            if (!name.isEmpty() && !date.isEmpty() && !time.isEmpty()) {
                Task newTask = new Task(name, date, time, priority);
                listModel.addElement(newTask);
                taskField.setText("");
                dateField.setText("");
                timeField.setText("");
            }
        });

        btnRemove.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        });
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    // Task class
    class Task {
        String name;
        String date;
        String time;
        String priority;

        public Task(String name, String date, String time, String priority) {
            this.name = name;
            this.date = date;
            this.time = time;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return name + " | " + date + " " + time + " | Priority: " + priority;
        }
    }

    // Renderer for color coding
    class TaskRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Task) {
                Task task = (Task) value;
                switch (task.priority) {
                    case "High":
                        comp.setForeground(Color.RED);
                        break;
                    case "Medium":
                        comp.setForeground(Color.ORANGE);
                        break;
                    default:
                        comp.setForeground(Color.BLACK);
                        break;
                }
            }
            return comp;
        }
    }
}
