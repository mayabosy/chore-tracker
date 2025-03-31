/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.time.*;

public class ChoresBarChart {

    public static void main() {
        ChoresDatabase choresDB = new ChoresDatabase();
        ArrayList<Chore> choreList = new ArrayList();
        int userOneCompletionTime = 10;
        int userTwoCompletionTime = 30;
        int week = 0;
        ArrayList<Integer> userOneCompletionTimeWeek = new ArrayList();
        ArrayList<Integer> userTwoCompletionTimeWeek = new ArrayList();
        LocalDate eachDayWeek = LocalDate.now();

        try {
            choreList = choresDB.selectChoresAssigned();
        } catch (Exception e) {
            System.out.println("Database connection  error" + e);
        }
        if (eachDayWeek.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            if (week <= 3) {
                week++;
            } else {
                week = 0;
            }

            userOneCompletionTime = 0;
            userTwoCompletionTime = 0;

        }

        for (Chore c : choreList) {
            if (c.getChoreAssignTo() == 1) {
                userOneCompletionTime += c.getCompletionTime();
                userOneCompletionTimeWeek.add(week, userOneCompletionTime);
            } else if (c.getChoreAssignTo() == 2) {
                userTwoCompletionTime += c.getCompletionTime();
                userTwoCompletionTimeWeek.add(week, userTwoCompletionTime);
            }

        }

        userOneCompletionTimeWeek.add(0, 25);
        userOneCompletionTimeWeek.add(1, 20);
        userOneCompletionTimeWeek.add(2, 30);
        userOneCompletionTimeWeek.add(3, 45);
        userTwoCompletionTimeWeek.add(0, 10);
        userTwoCompletionTimeWeek.add(1, 30);
        userTwoCompletionTimeWeek.add(2, 25);
        userTwoCompletionTimeWeek.add(3, 35);
        
        // some  dummydata
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(userOneCompletionTimeWeek.get(0), "Week 1", "Bob");
        dataset.addValue(userOneCompletionTimeWeek.get(1), "Week 2", "Bob");
        dataset.addValue(userOneCompletionTimeWeek.get(2), "Week 3", "Bob");
        dataset.addValue(userOneCompletionTimeWeek.get(3), "Week 4", "Bob");
        dataset.addValue(userTwoCompletionTimeWeek.get(0), "Week 1", "Alice");
        dataset.addValue(userTwoCompletionTimeWeek.get(1), "Week 2", "Alice");
        dataset.addValue(userTwoCompletionTimeWeek.get(2), "Week 3", "Alice");
        dataset.addValue(userTwoCompletionTimeWeek.get(3), "Week 4", "Alice");
        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Time Spent on Chores Each Week", // the chart Chart title
                "User", // the X-axis label
                "Time Spent (hours)", // Y-axiss label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation
                true, // Show legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        // Customize chart
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.GREEN);
        chart.getCategoryPlot().getRenderer().setSeriesPaint(1, Color.BLUE);

        //  panel to display thechart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 400));

        // Create window to display panel
        JFrame frame = new JFrame("Chores Bar Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
