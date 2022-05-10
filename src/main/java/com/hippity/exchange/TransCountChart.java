package com.hippity.exchange;

import com.hippity.exchange.api.ExchangeService;
import com.hippity.exchange.api.model.Item;
import com.hippity.exchange.api.model.Statistics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;


public class TransCountChart extends Application {

    @Override public void start(Stage stage) {
        stage.setTitle("Transaction Counts");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Days 1 through 20");
        yAxis.setLabel("Number of transactions on each day");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Graph showing transaction counts per day for the last 20 days");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Buying USD Count");
        //populating the series with data


        ExchangeService.exchangeApi().getStats()
                .enqueue(new Callback<Statistics>() {
                    @Override
                    public void onResponse(Call<Statistics> call,
                                           Response<Statistics> response) {

                        for (int i=0; i<20; i++){

                            series.getData().add(new XYChart.Data(i, response.body().getBuyCount().get(i)));

                        }

                    }
                    @Override
                    public void onFailure(Call<Statistics> call,
                                          Throwable throwable) {
                    }
                });



        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);



        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Selling USD Count");
        //populating the series with data


        ExchangeService.exchangeApi().getStats()
                .enqueue(new Callback<Statistics>() {
                    @Override
                    public void onResponse(Call<Statistics> call,
                                           Response<Statistics> response) {

                        for (int i=0; i<20; i++){

                            series2.getData().add(new XYChart.Data(i, response.body().getSellCount().get(i)));

                        }

                    }
                    @Override
                    public void onFailure(Call<Statistics> call,
                                          Throwable throwable) {
                    }
                });


        lineChart.getData().add(series2);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void run(){

    }
}