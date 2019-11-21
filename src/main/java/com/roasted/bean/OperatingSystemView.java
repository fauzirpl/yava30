package com.roasted.bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import com.roasted.ejb.OSAmbariJMXUtil;
import com.roasted.ejb.OSRestDatasetClient;
import com.roasted.model.OperatingSystem;

@ManagedBean
@RequestScoped
public class OperatingSystemView implements Serializable {

	static final long serialVersionUID = 1237l;

	private LineChartModel lineModel;

	static Logger logger = Logger.getLogger(OperatingSystemView.class);

	static String host = "192.168.3.132";
	static String port = "50075";
	static String qry_name = "java.lang:type=OperatingSystem";

	private List<OperatingSystem> _bbs_;

	OSRestDatasetClient _client = new OSRestDatasetClient(host, port);

	OperatingSystem _fs = new OperatingSystem();

	@PostConstruct
	public void init() {
		getJSON();
		createLineModel();
	}

	public void getJSON() {

		try {

			String qry_name = "java.lang:type=OperatingSystem";

			String _url = "http://" + host + ":" + port + "/jmx?qry=" + qry_name;

			String json = _client.ambari_plain_client(_url);

			_bbs_ = OSAmbariJMXUtil.json2OperatingSystem(json);

			_bbs_.size();

			for (OperatingSystem i : _bbs_) {

				_fs = i;

			}

		} catch (Exception e) {
			logger.error(e);
		}

	}

	@Schedule(minute = "*/5", hour = "*", persistent = false)
	public void atSchedule() throws InterruptedException {

		getJSON();

		System.out.println("DeclarativeScheduler:: In atSchedule() fired getJSON()");

	}

	

	public void createLineModel() {
		lineModel = new LineChartModel();
		ChartData data = new ChartData();
		
		Double op1 = 1073741824.0;

		LineChartDataSet dataSet1 = new LineChartDataSet();
		List<Number> values1 = new ArrayList<>();
		
		Double t = _fs.getTotalPhysicalMemorySize() / op1;
		DecimalFormat dfT = new DecimalFormat("#.#");
		t = Double.valueOf(dfT.format(t));
		
		for(int i=0; i<_bbs_.size();i++) {
			values1.add(t);
			values1.add(t);
		}
//        values.add(65);
//        values.add(59);
//        values.add(80);
//        values.add(81);
//        values.add(56);
//        values.add(55);
//        values.add(40);
		dataSet1.setData(values1);
		dataSet1.setFill(false);
		dataSet1.setLabel("Total");
		dataSet1.setBorderColor("rgb(0, 49, 130)");
		dataSet1.setLineTension(0.1);
		data.addChartDataSet(dataSet1);
		
		LineChartDataSet dataSet2 = new LineChartDataSet();
		List<Number> values2 = new ArrayList<>();
		
		Double f = _fs.getFreeSwapSpaceSize() / op1;
		DecimalFormat dfF = new DecimalFormat("#.#");
		f = Double.valueOf(dfF.format(f));
		
		for(int i=0; i<_bbs_.size();i++) {
			values2.add(f);
			values2.add(f);
		}
		
		dataSet2.setData(values2);
		dataSet2.setFill(false);
		dataSet2.setLabel("Swap");
		dataSet2.setBorderColor("rgb(242, 226, 0)");
		dataSet2.setLineTension(0.1);
		data.addChartDataSet(dataSet2);

//		List<String> labels = new ArrayList<>();
//		labels.add("January");
//		labels.add("February");
//		labels.add("March");
//		labels.add("April");
//		labels.add("May");
//		labels.add("June");
//		labels.add("July");
//		data.setLabels(labels);

		// Options
//		LineChartOptions options = new LineChartOptions();
//		Title title = new Title();
//		title.setDisplay(true);
//		options.setTitle(title);
//
//		lineModel.setOptions(options);
		lineModel.setData(data);
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public List<OperatingSystem> get_bbs_() {
		return _bbs_;
	}

	public void set_bbs_(List<OperatingSystem> _bbs_) {
		this._bbs_ = _bbs_;
	}

}
