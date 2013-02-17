package br.com.sistemaweb.reporting;

import java.io.IOException;

import br.com.sistemaweb.utils.Utils;

public class SessionSaveReport implements ISaveReport {

	@Override
	public void save(Report report) throws IOException {
		Utils.saveToSession(report.getReportName(),report.getExportType(),report.getReportOutputStream().toByteArray());
		report.getReportOutputStream().close();
	}

}
