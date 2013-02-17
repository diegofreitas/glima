package br.com.sistemaweb.reporting;

import java.io.IOException;

import br.com.sistemaweb.utils.Configuration;
import br.com.sistemaweb.utils.Utils;

public class HDSaveReport implements ISaveReport {

	@Override
	public void save(Report report) throws IOException {
		String filename = Configuration.valueFor("temp.path")+report.getReportName()+report.getExportType().getExtension();
		Utils.bytesToFile(filename, report.getReportOutputStream().toByteArray());
	}

}
