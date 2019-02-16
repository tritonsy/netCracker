package building;

import building.facrotries.DwellingFactory;
import building.interfaces.Building;
import building.interfaces.Floor;
import building.interfaces.Space;
import building.utlility.Buildings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class MainForm extends JFrame {

    private JPanel rootPanel;
    private JPanel buildingPanel;
    private JPanel floorPanel;
    private JPanel spacePanel;
    private JPanel buildingSchema;
    private JTextArea buildingArea;
    private JTextArea floorArea;
    private JTextArea spaceArea;

    private MainForm() throws HeadlessException {
        ActionListener fileActionListener = event -> {
            String type;
            switch (event.getActionCommand()) {
                case "Open dwelling":
                    type = "Dwelling";
                    Buildings.setFactory(new DwellingFactory());
                    break;
                case "Open office building":
                    type = "Office building";
                    Buildings.setFactory(new DwellingFactory());
                    break;
                default:
                    type = "unknown type";
                    break;
            }
            JFileChooser openedFile = new JFileChooser();
            int result = openedFile.showDialog(MainForm.this, "Выберите файл");
            if (result == JFileChooser.APPROVE_OPTION) {
                try (Scanner fileScanner = new Scanner(openedFile.getSelectedFile())) {
                    initFrames(Buildings.readBuilding(fileScanner), type);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(MainForm.this, exception);
                }
            }
        };

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu themeMenu = new JMenu("Look&Feel");

        JMenuItem itm = new JMenuItem("Open dwelling");
        itm.addActionListener(fileActionListener);
        fileMenu.add(itm);

        fileMenu.add(new JSeparator());

        itm = new JMenuItem("Open office building");
        itm.addActionListener(fileActionListener);
        fileMenu.add(itm);

        ButtonGroup group = new ButtonGroup();

        ActionListener lookActionListener = event -> {
            try {
                switch (event.getActionCommand()) {
                    case "Metal":
                        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                        SwingUtilities.updateComponentTreeUI(MainForm.this.getContentPane());
                        break;
                    case "GTK":
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                        SwingUtilities.updateComponentTreeUI(MainForm.this.getContentPane());
                        break;
                    case "Nimbus":
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                        SwingUtilities.updateComponentTreeUI(MainForm.this.getContentPane());
                        break;
                }
            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        };

        JRadioButtonMenuItem windowsItem = new JRadioButtonMenuItem("Metal");
        windowsItem.addActionListener(lookActionListener);
        group.add(windowsItem);
        themeMenu.add(windowsItem);

        JRadioButtonMenuItem motifItem = new JRadioButtonMenuItem("GTK");
        motifItem.addActionListener(lookActionListener);
        group.add(motifItem);
        themeMenu.add(motifItem);

        JRadioButtonMenuItem nimbusItem = new JRadioButtonMenuItem("Nimbus");
        nimbusItem.addActionListener(lookActionListener);
        group.add(nimbusItem);
        themeMenu.add(nimbusItem);

        menuBar.add(fileMenu);
        menuBar.add(themeMenu);
        setJMenuBar(menuBar);
        setTitle("NetCracker");
        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setVisible(true);
    }

    private void initFrames(Building building, String buildingType) {
        System.out.println(building);
        buildingArea.setText(
                "Building Type " + buildingType + "\n"
                        + "Floor amount " + building.getFloorCount() + "\n"
                        + "All Square " + building.getSpace() + "\n");
        setInfo(building, 0, 0);
        Floor[] floors = building.getFloors();

        buildingSchema.setLayout(new BoxLayout(buildingSchema, BoxLayout.Y_AXIS));

        for (int i = 0; i < floors.length; i++) {
            JPanel floorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            floorPanel.setBorder(BorderFactory.createEtchedBorder());
            Space[] spaces = floors[i].getSpaces();
            for (int j = 0; j < spaces.length; j++) {
                JButton button = new JButton(j + "");
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> setInfo(building, finalI, finalJ));
                floorPanel.add(button);
            }
            floorPanel.setPreferredSize(floorPanel.getPreferredSize());
            JScrollPane floorPane = new JScrollPane(floorPanel);
            buildingSchema.add(floorPane);
        }
    }

    private void setInfo(Building building, int i, int j) {
        floorArea.setText("Floor №: " + i + "\n" +
                "Space amount " + building.getFloor(i).getSpaceCount() + "\n" +
                "All Square " + building.getFloor(i).getSquare());
        spaceArea.setText("Space № " + j + "\n" +
                "Square " + building.getFloor(i).getSpace(j).getSquare() + "\n" +
                "Room Amount " + building.getFloor(i).getSpace(j).getRoomAmount());
    }

    public static void main(String[] args) {
        new MainForm();
    }

}
