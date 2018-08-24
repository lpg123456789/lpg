package com.lpg.moudle.attributeMoule.attribute.sacredBook;

import java.util.HashMap;

import com.lpg.moudle.attributeMoule.IAttributeProvider;
import com.lpg.moudle.attributeMoule.attribute.consts.AttributeSystem;
import com.lpg.moudle.attributeMoule.attribute.role.IMateRole;
import com.lpg.moudle.attributeMoule.attribute.utils.AttributeHelper;

public class SacredBookModule implements IAttributeProvider {

	private static SacredBookModule instance;

	public static SacredBookModule getInstance() {
		if (instance == null) {
			instance = new SacredBookModule();
		}
		return instance;
	}

	@Override
	public HashMap<String, Number> getAttributes(IMateRole mate) {
		return null;
	}

	public void libramsWear(IMateRole mate) {
		
		AttributeHelper.setRoleAttributes(mate, SacredBookModule.getInstance().getAttributes(mate), AttributeSystem.SACREDBOOK, true);
		
	}

}
