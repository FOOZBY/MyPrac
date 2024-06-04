import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyFrame extends JFrame {
    MyPanel panel;
    File opened_file;

    public void union()
    {

    }
    public void exclusion()
    {

    }

    public void get_points_from_file(File file)
    {
        int npoints=0;
        int[] xpoints;
        int[] ypoints;
        boolean hasA_n = false;
        boolean hasA_x = false;
        boolean hasA_y = false;
        boolean hasB_n = false;
        boolean hasB_x = false;
        boolean hasB_y = false;
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();

            if (s.contains("#1:"))
            {
                s = s.substring(3);

                npoints = Integer.parseInt(s);
                panel.pol_A.npoints=npoints;
            }
            if (s.contains("#2:"))
            {
                s = s.substring(3);

                npoints = Integer.parseInt(s);
                panel.pol_B.npoints=npoints;
            }
            if (s.contains("x1:"))
            {
                s = s.substring(3);

                String[] points = s.split(",");
                xpoints = new int[s.length()];
                for (int i = 0; i < points.length; i++) {
                    xpoints[i] = Integer.parseInt(points[i]);
                }
                panel.pol_A.xpoints = xpoints;
            }
            if (s.contains("y1:"))
            {
                s = s.substring(3);
                String[] points = s.split(",");
                ypoints = new int[s.length()];
                for (int i = 0; i < points.length; i++) {
                    ypoints[i] = Integer.parseInt(points[i]);
                }
                panel.pol_A.ypoints = ypoints;
            }
            if (s.contains("x2:"))
            {
                s = s.substring(3);

                String[] points = s.split(",");
                xpoints = new int[s.length()];
                for (int i = 0; i < points.length; i++) {
                    xpoints[i] = Integer.parseInt(points[i]);
                }
                panel.pol_B.xpoints = xpoints;
            }
            if (s.contains("y2:"))
            {
                s = s.substring(3);
                String[] points = s.split(",");
                ypoints = new int[s.length()];
                for (int i = 0; i < points.length; i++) {
                    ypoints[i] = Integer.parseInt(points[i]);
                }
                panel.pol_B.ypoints = ypoints;
            }
        }
        repaint();
    }

    MyFrame(){
        super.setLayout(new BorderLayout());
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setTitle("Practice");
        super.setVisible(true);
        super.setSize(700,700);
        super.setLocationRelativeTo(null);

        panel = new MyPanel();

        JMenuBar menu_bar = new JMenuBar();

        JMenu file_menu = new JMenu("File");
        JMenu help_menu = new JMenu("Help");
        JMenu actions_menu = new JMenu("Actions");


        JMenuItem open_file_item = new JMenuItem("Open File");
        open_file_item.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            File file;
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION){
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                opened_file = file;
                get_points_from_file(opened_file);
            }
        });
        JMenuItem save_file_item = new JMenuItem("Save File");
        save_file_item.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            File file;
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION){
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
            }
        });
        JMenuItem help_note = new JMenuItem("Help Note");
        help_note.addActionListener(e -> {
            System.out.println("чё непонятного?");
        });
        JMenuItem union = new JMenuItem("Union");
        union.addActionListener(e -> {
            System.out.println("union the polygons");
            union();
        });
        JMenuItem exclusion = new JMenuItem("Exclusion");
        exclusion.addActionListener(e -> {
            System.out.println("exclusion the polygons");
            exclusion();
        });

        file_menu.add(open_file_item);
        file_menu.add(save_file_item);
        help_menu.add(help_note);
        actions_menu.add(union);
        actions_menu.add(exclusion);

        menu_bar.add(file_menu);
        menu_bar.add(help_menu);
        menu_bar.add(actions_menu);

        JPanel menu_panel = new JPanel();
        menu_panel.setLayout(new BorderLayout());
        menu_panel.add(menu_bar, BorderLayout.WEST);
        menu_panel.setPreferredSize(new Dimension(500,25));


        super.setJMenuBar(menu_bar);
        super.add(menu_panel, BorderLayout.NORTH);
        super.add(panel, BorderLayout.CENTER);
    }
}
