/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kf5012assessment;

/**
 *
 * @author nihal
 */
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PiePlot;

public class ChoresPieChart {

    public static void main() {

        ChoresDatabase choresDB = new ChoresDatabase();
        ArrayList<Chore> choreList = new ArrayList();
        int user1Chores = 90;
        int user2Chores = 4;
        

        try {
            choreList = choresDB.selectChoresAssigned();
        } catch (Exception e) {
            System.out.println("Database connection  error " + e);
        }
        for (Chore c : choreList) {
            if (c.getChoreAssignTo() == 1) {
                user1Chores++;
            } else {
                user2Chores++;
            }
        }

        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("User 1", user1Chores);
        dataset.setValue("User 2", user2Chores);

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Done Chores List", // Chart title
                dataset, // Dataset
                true, // Show legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        // Customize chart
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("User 1", Color.GREEN);
        plot.setSectionPaint("User 2", Color.BLUE);
        plot.setExplodePercent("User 1", 0.1);
        plot.setSimpleLabels(true);

        // Create panel to display chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 400));

        // Create window to display panel
        JFrame frame = new JFrame("Chores Pie Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);

    }

}
