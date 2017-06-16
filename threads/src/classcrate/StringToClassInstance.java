package classcrate;

public class StringToClassInstance {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		String ID= "ID4";
		
		ClassEnums idValue = ClassEnums.valueOf(ID);
		
		Class<?> classToCreate = idValue.getClassToCreate();
		
		CommonBehavior newInstance = (CommonBehavior) classToCreate.newInstance();
		
		newInstance.sayHello();	}
}
