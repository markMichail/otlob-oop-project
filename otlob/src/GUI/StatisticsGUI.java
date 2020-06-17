/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import otlob.OTLOB;
import static otlob.OTLOB.AdminHashmap;
import static otlob.OTLOB.Ads;
import static otlob.OTLOB.Allrest;
import static otlob.OTLOB.CustomerHashmap;
import static otlob.OTLOB.DealHashmap;
import static otlob.OTLOB.WaiterHashmap;

/**
 *
 * @author ahmed
 */
public class StatisticsGUI extends JFrame {

    private JButton Back = new JButton("Back");
    private ChartFrame frame;

    public StatisticsGUI() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < OTLOB.nRArr.size(); i++) {
            dataset.setValue(OTLOB.nRArr.get(i).rating, "reviews", OTLOB.nRArr.get(i).name);

        }
        JFreeChart chart = ChartFactory.createBarChart("                      "
                + "                           restauraunts stats ", "restauraaunt name", "Rating Out Of 5", dataset, PlotOrientation.VERTICAL, false, true, false);
        frame = new ChartFrame("bar chart for reviews", chart);

        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        frame.setSize(400, 700);
        frame.setVisible(true);
        frame.add(Back);
        Back.addActionListener(new ButtonListener());
        frame.addWindowListener(new ButtonListener());
    }

    private class ButtonListener extends WindowAdapter implements ActionListener {

        @Override
        public void windowClosing(WindowEvent e) {
            try {
                ObjectOutputStream ObjOut;
                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.restsArray));
                ObjOut.writeObject(Allrest);
                ObjOut.close();

                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.adminsHashmap));
                ObjOut.writeObject(AdminHashmap);
                ObjOut.close();

                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.customersHashmap));
                ObjOut.writeObject(CustomerHashmap);
                ObjOut.close();

                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.waitersHashmap));
                ObjOut.writeObject(WaiterHashmap);
                ObjOut.close();

                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.dealsHashmap));
                ObjOut.writeObject(DealHashmap);
                ObjOut.close();

                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.ad));
                ObjOut.writeObject(Ads);
                ObjOut.close();

            } catch (IOException ex) {
                Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(Back)) {
                MainMenuGUI mainMenuGUI = new MainMenuGUI(OTLOB.Customer);
                frame.setVisible(false);
                mainMenuGUI.setVisible(true);
            }
        }

    }
}
