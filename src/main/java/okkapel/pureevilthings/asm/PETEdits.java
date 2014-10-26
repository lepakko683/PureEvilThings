package okkapel.pureevilthings.asm;


import scala.actors.threadpool.Arrays;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.ModMetadata;

public class PETEdits extends DummyModContainer {
	
	public PETEdits() {
		super(new ModMetadata());
		
		ModMetadata meta = getMetadata();
		meta.modId = "petedits";
		meta.name = "PETEdits";
		meta.version = "@VERSION@";
		meta.authorList = Arrays.asList(new String[]{"okkapel"});
		meta.description = "";
	}
	

}
