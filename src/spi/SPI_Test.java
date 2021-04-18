package spi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ServiceLoader;

public class SPI_Test {
	public static void main(String[] args) {
		ServiceLoader<Service> services = ServiceLoader.load(Service.class);
		Iterator<Service> service_iterator = services.iterator();
		while (service_iterator.hasNext()){
			Service service = service_iterator.next();
			service.read();
			service.load();
			service.exit();
		}
		
		new HashMap<String,String>();
	}
}
