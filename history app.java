import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DiaryWithImages {

    private JFrame frame;
    private JTextArea textArea;
    private JLabel imageLabel;
    private File selectedImage;

    public DiaryWithImages() {
        frame = new JFrame("Diary App with Images");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();

        JButton uploadBtn = new JButton("Upload Picha");
        JButton saveBtn = new JButton("Save Diary");

        imageLabel = new JLabel("Hakuna picha iliyochaguliwa");

        panel.add(uploadBtn);
        panel.add(saveBtn);

        frame.add(panel, BorderLayout.SOUTH);
        frame.add(imageLabel, BorderLayout.NORTH);

        // Upload image
        uploadBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);

            if (option == JFileChooser.APPROVE_OPTION) {
                selectedImage = fileChooser.getSelectedFile();
                imageLabel.setText("Picha: " + selectedImage.getName());
            }
        });

        // Save diary
        saveBtn.addActionListener(e -> saveEntry());

        frame.setVisible(true);
    }

    private void saveEntry() {
        try {
            FileWriter fw = new FileWriter("diary.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Diary:");
            bw.newLine();
            bw.write(textArea.getText());
            bw.newLine();

            if (selectedImage != null) {
                bw.write("Picha: " + selectedImage.getAbsolutePath());
                bw.newLine();
            }

            bw.write("------------------------");
            bw.newLine();

            bw.close();

            JOptionPane.showMessageDialog(frame, "Imehifadhiwa!");
            textArea.setText("");
            imageLabel.setText("Hakuna picha iliyochaguliwa");
            selectedImage = null;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new DiaryWithImages();
    }
}