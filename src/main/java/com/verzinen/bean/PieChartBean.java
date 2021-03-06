package com.verzinen.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.chartistjsf.model.chart.PieChartModel;
import org.primefaces.event.ItemSelectEvent;

@ManagedBean
public class PieChartBean {
    private PieChartModel pieChartModel;
    public PieChartBean() {
        createPieChart();
    }
    public void createPieChart() {
        pieChartModel = new PieChartModel();
        pieChartModel.addLabel("Area 1");
        pieChartModel.addLabel("Area 2");
        pieChartModel.addLabel("Area 3");
        pieChartModel.set(20);
        pieChartModel.set(25);
        pieChartModel.set(15);
        pieChartModel.setShowTooltip(true);
    }
    public void pieItemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "
                + pieChartModel.getData().get(event.getItemIndex()));
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    }
    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }
    public void setPieChartModel(PieChartModel pieChartModel) {
        this.pieChartModel = pieChartModel;
    }
}