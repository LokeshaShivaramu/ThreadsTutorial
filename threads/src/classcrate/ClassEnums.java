package classcrate;

public enum ClassEnums {
	
	ID1(ClassOne.class), ID2(ClassTwo.class), ID3(ClassThree.class), ID4(ClassFour.class);
	
	private Class<? extends CommonBehavior> classToCreate;
	
	ClassEnums(Class<? extends CommonBehavior> toInstantiate)
	{
		classToCreate = toInstantiate;
	}

	
	Class<? extends CommonBehavior> getClassToCreate(){
		return classToCreate;
	}
}
