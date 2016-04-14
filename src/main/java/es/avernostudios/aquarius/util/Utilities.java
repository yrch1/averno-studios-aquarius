package es.avernostudios.aquarius.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class Utilities {

	private static Logger log = Logger.getLogger(Utilities.class);

	private static Utilities INSTANCE = new Utilities();

	/**
	 * 
	 */
	private Utilities() {
	}

	public static Utilities getInstance() {
		return INSTANCE;
	}

	/**
	 * Obtiene la fecha en formato para los filtros de magento
	 * 
	 * @return
	 */
	public String getFilterDateMinusMonth(int month) {
		String result = "2014-01-01 00:00:00";
		try {
			Calendar rigthNow = Calendar.getInstance();

			rigthNow.add(Calendar.MONTH, -month);

			result = getFilterDate(rigthNow.getTime());
		} catch (Exception e) {
			log.error("Exception", e);
		}
		return result;
	}

	/**
	 * Obtiene la fecha en formato para los filtros de magento
	 * 
	 * @return
	 */
	public String getFilterDateMinus6Month() {
		String result = "2014-01-01 00:00:00";
		try {
			Calendar rigthNow = Calendar.getInstance();

			rigthNow.add(Calendar.MONTH, -1);

			result = getFilterDate(rigthNow.getTime());
		} catch (Exception e) {
			log.error("Exception", e);
		}
		return result;
	}

	/**
	 * Obtiene la fecha en formato para los filtros de magento
	 * 
	 * @return
	 */
	public String getFilterDate(Date fecha) {
		String result = "2014-01-01 00:00:00";
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			result = sdf.format(fecha);
		} catch (Exception e) {
			log.error("Exception", e);
		}
		return result;
	}

	public String getFilterDateFrom(int dateFrom) {
		String result = "2014-01-01 00:00:00";
		Calendar rightNow = Calendar.getInstance();
		try {
			switch (dateFrom) {
			case 1:
			default:
				rightNow.add(Calendar.DATE, -1);
				break;
			case 2:
				rightNow.add(Calendar.DATE, -7);
				break;
			case 3:
				rightNow.add(Calendar.MONTH, -1);
				break;
			case 4:
				rightNow.add(Calendar.YEAR, -1);
				break;
			}

			result = getFilterDate(rightNow.getTime());
		} catch (Exception e) {
			log.error("Exception", e);
		}
		return result;

	}
}
