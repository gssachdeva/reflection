package reflection;

import java.lang.reflect.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ReflectionOps {
	
	@Test
	public void getClassNameFromObject() {
		Object ferrari = new Ferrari("Ferrari");
		
		Class<?> clazz = ferrari.getClass();

		assertEquals("Ferrari", clazz.getSimpleName());
		assertEquals("reflection.Ferrari", clazz.getName());
		assertEquals("reflection.Ferrari", clazz.getCanonicalName());		
	}
	
	@Test
	public void createObjectFromClassName() {
		try {
			Class<?> clazz = Class.forName("reflection.Ferrari");
			
			assertEquals("Ferrari", clazz.getSimpleName());
			assertEquals("reflection.Ferrari", clazz.getName());
			assertEquals("reflection.Ferrari", clazz.getCanonicalName());	
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Error: ClassNotFoundException");
		}				
	}
	
	@Test
	public void getModifiersFromObject() {
		try {
			Class<?> ferrariClass = Class.forName("reflection.Ferrari");
			Class<?> carClass = Class.forName("reflection.Car");
			
			int ferrariMods = ferrariClass.getModifiers(); 
			int carMods = carClass.getModifiers();
			
			assertTrue(Modifier.isPublic(ferrariMods));
		    assertTrue(Modifier.isAbstract(carMods));
		    assertTrue(Modifier.isPublic(carMods));
				
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Error: ClassNotFoundException");
		}		
	}
	
	@Test
	public void getPackageInfoFromClass() {
		Ferrari ferrari = new Ferrari("Ferrari");
		Class<?> ferrariClass = ferrari.getClass();
		Package pkg = ferrariClass.getPackage();
		
		assertEquals("reflection", pkg.getName());	
	}
	
	@Test
	public void getSuperClassNameFromObject() {
		Object ferrari = new Ferrari("Ferrari");
		String foo = "foo";
		
		Class<?> ferrariClazz = ferrari.getClass();
		Class<?> ferrariSuperClazz = ferrariClazz.getSuperclass();

		assertEquals("Car", ferrariSuperClazz.getSimpleName());
		assertEquals("Object", foo.getClass().getSuperclass().getSimpleName());		
	}

	@Test
	public void getInterfacesFromClass() {
		try {
			Class<?> carClass = Class.forName("reflection.Car");
			Class<?> ferrariClass = Class.forName("reflection.Ferrari");
						
			Class<?>[] carInterfaces = carClass.getInterfaces();
			Class<?>[] ferrariInterfaces = ferrariClass.getInterfaces();
			
			assertEquals(1, carInterfaces.length);
		    assertEquals(1, ferrariInterfaces.length);
		    assertEquals("Vehicle", carInterfaces[0].getSimpleName());
		    assertEquals("Racing", ferrariInterfaces[0].getSimpleName());
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Error: ClassNotFoundException");
		}		
	}
	
	@Test
	public void getCtorFromClass() {
		try {
			Class<?> ferrariClass = Class.forName("reflection.Ferrari");
						
			Constructor<?>[] ferrariCtors = ferrariClass.getConstructors();
						
			assertEquals(1, ferrariCtors.length);
		    assertEquals("reflection.Ferrari", ferrariCtors[0].getName());
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Error: ClassNotFoundException");
		}		
	}
	
	@Test
	public void getFieldsFromClass() {
		try {
			Class<?> civicClass = Class.forName("reflection.Civic");
						
			Field[] civicFields = civicClass.getFields();
						
			assertEquals(1, civicFields.length);
			assertEquals("TYPE", civicFields[0].getName());
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Error: ClassNotFoundException");
		}		
	}
	
	@Test
	public void getFieldFromName() {
		try {
			Class<?> civicClass = Class.forName("reflection.Civic");
						
			Field civicField = civicClass.getField("TYPE");
									
			assertEquals("TYPE", civicField.getName());
		}
		catch(Exception exp) {
			System.out.println("Error: Exception");
		}		
	}
	
	@Test
	public void getDeclaredFieldsFromClass() {
		try {
			Class<?> civicClass = Class.forName("reflection.Civic");
						
			Field[] civicFields = civicClass.getDeclaredFields();
						
			assertEquals(2, civicFields.length);
			
			for (Field field : civicFields) {
				String fieldName = field.getName();
				assertTrue(fieldName.equals("mileage") || 
						fieldName.equals("price")); 
			}
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Error: ClassNotFoundException");
		}		
	}
	
	@Test
	public void getDeclaredFieldFromName() {
		try {
			Class<?> civicClass = Class.forName("reflection.Civic");
						
			Field civicField = civicClass.getDeclaredField("mileage");
						
			assertEquals("mileage", civicField.getName());
		}
		catch(Exception exp) {
			System.out.println("Error: Exception");
		}		
	}
	
	@Test
	public void getFieldType() {
		try {
			Class<?> civicClass = Class.forName("reflection.Civic");
						
			Field civicField = civicClass.getDeclaredField("mileage");
			
			Class<?> civicFieldClass = civicField.getType();
						
			assertEquals("int", civicFieldClass.getSimpleName());
		}
		catch(Exception exp) {
			System.out.println("Error: Exception");
		}		
	}
	
	@Test
	public void getFieldAndModify() {
		try {
			Class<?> civicClass = Class.forName("reflection.Civic");	
			Civic civic = (Civic) civicClass.getConstructor().newInstance();
			Field civicField = civicClass.getDeclaredField("mileage");
			civicField.setAccessible(true);
			
			assertEquals(15, civicField.getInt(civic));
			civicField.set(civic, 20);
			assertEquals(20, civicField.getInt(civic));			
		}
		catch(Exception exp) {
			System.out.println("Error: Exception");
		}		
	}
	
	@Test
	public void getPublicMethodsFromClass() {
		try {
			Class<?> carClass = Class.forName("reflection.Car");
						
			Method[] carMethods = carClass.getDeclaredMethods();
						
			assertEquals(3, carMethods.length);
			
			for (Method method : carMethods) {
				String methodName = method.getName();
				assertTrue(methodName.equals("getMake") || 
						methodName.equals("setMake") || 
						methodName.equals("getMileage"));
			}			
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Error: ClassNotFoundException");
		}		
	}
	
	@Test
	public void getAllMethodsFromClass() {
		try {
			Class<?> civicClass = Class.forName("reflection.Civic");
						
			Method[] civicMethods = civicClass.getMethods();
						
			List<String> civicMethodNames = new ArrayList<>();
		    
			for (Method method : civicMethods) {
				civicMethodNames.add(method.getName());				
			}
			
			assertTrue(civicMethodNames.containsAll(Arrays.asList(
					"equals", "notifyAll", "notify", "getClass", "hashCode", 
					"toString", "wait", "wait", "wait", "getVehicleType", 
					"getPrice", "setMileage", "getMake", "setMake")));	
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Error: ClassNotFoundException");
		}		
	}
	
	@Test
	public void getAllCtorsFromClass() {
		try {
			Class<?> civicClass = Class.forName("reflection.Civic");
						
			Constructor<?>[] civicCtors = civicClass.getConstructors();
						
			assertEquals(3, civicCtors.length);		    
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Error: ClassNotFoundException");
		}		
	}
	
	@Test
	public void getAllCtorsFromClassByParamTypes() {
		try {
			Class<?> civicClass = Class.forName("reflection.Civic");
						
			Constructor<?> civicCtor1 = civicClass.getConstructor();
			Constructor<?> civicCtor2 = civicClass.getConstructor(String.class);
			Constructor<?> civicCtor3 = civicClass.getConstructor(String.class, int.class);
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Error: ClassNotFoundException");
		}	
		catch(NoSuchMethodException expNSM) {
			System.out.println("Error: NoSuchMethoException");
		}
	}
	
	@Test
	public void instantiateObjectsAtRuntime() {
		try {
			Class<?> civicClass = Class.forName("reflection.Civic");
						
			Constructor<?> civicCtor1 = civicClass.getConstructor();
			Constructor<?> civicCtor2 = civicClass.getConstructor(String.class);
			Constructor<?> civicCtor3 = civicClass.getConstructor(String.class, int.class);
			
			Civic civic1 = (Civic) civicCtor1.newInstance();
			Civic civic2 = (Civic) civicCtor2.newInstance("Honda Civic Automatic");
			Civic civic3 = (Civic) civicCtor3.newInstance("Honda Civic Automatic", 13);
			
			assertEquals("Honda Civic", civic1.getMake());
			assertEquals(15, civic2.getMileage());
			assertEquals(13, civic3.getMileage());
			assertEquals("Car", civic3.getVehicleType());
		}
		catch(Exception exp) {
			System.out.println("Error: Exception");
		}	
	}
	
	@Test
	public void accessAndModifyFieldValues() {
		try {
			Class<?> civicClass = Class.forName("reflection.Civic");
			Civic civic = (Civic) civicClass.getConstructor().newInstance();
			Field field = civicClass.getDeclaredField("mileage");
			field.setAccessible(true);
			
			assertEquals(15, field.getInt(civic));
			assertEquals(15, civic.getMileage());
			
			field.setInt(civic, 20);
			
			assertEquals(20, field.getInt(civic));
			assertEquals(20, civic.getMileage());
		}
		catch(Exception exp) {
			System.out.println("Error: ClassNotFoundException");
		}	
	}
	
	@Test
	public void getAndInvokeMethod() {
		try {
			Civic civic = new Civic();
			
			Method accessMileageMethod = civic.getClass().getDeclaredMethod("getMileage");
		    Method setMileageMethod = civic.getClass().getDeclaredMethod("setMileage", int.class);
		    
		    Integer mileage = (Integer) accessMileageMethod.invoke(civic);
		    assertEquals(15, mileage.intValue());
		    
		    setMileageMethod.invoke(civic, 20);
		    
		    Integer newMileage = (Integer) accessMileageMethod.invoke(civic);
		    assertEquals(20, newMileage.intValue());
		}
		catch(Exception exp) {
			System.out.println("Error: Exception");
		}		
	}
}
