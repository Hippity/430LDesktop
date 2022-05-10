package com.hippity.exchange.changes;

import com.hippity.exchange.Authentication;
import com.hippity.exchange.OnPageCompleteListener;
import com.hippity.exchange.PageCompleter;
import com.hippity.exchange.api.ExchangeService;
import com.hippity.exchange.api.model.Token;
import com.hippity.exchange.api.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Arrays;
import java.util.List;

public class changes implements PageCompleter {
    private OnPageCompleteListener onPageCompleteListener;
    public TextField usernameTextField;
    public TextField passwordTextField;
    public void setOnPageCompleteListener(OnPageCompleteListener onPageCompleteListener) {
        this.onPageCompleteListener = onPageCompleteListener;
    }

    public void buildGraph(ActionEvent actionEvent) {

        XYChart.Series<Double, Double> series = new XYChart.Series<>();

        /*
        for(  )
        {
            // Holds x, y data for a single entry in the series.
            XYChart.Data<Double, Double> data = new XYChart.Data<>();

            // Set the x, y data.
            data.setXValue( segment.x );
            data.setYValue( segment.y );

            // Add the data to the series.
            series.getData().add( data );
        }

        return series;

         */

    }
}