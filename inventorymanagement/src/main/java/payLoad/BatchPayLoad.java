package payLoad;

import com.ste.inventorymanagement.model.Batch;

public class BatchPayLoad {
	private Batch batch;
	private String materialDescription;
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public String getMaterialDescription() {
		return materialDescription;
	}
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}
	
	
}
