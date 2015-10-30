package org.cyk.system.bakery.ui.web.primefaces.structure;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.cyk.system.bakery.model.structure.BakeryUnit;
import org.cyk.system.bakery.model.structure.EnergyType;
import org.cyk.system.bakery.ui.web.primefaces.model.BakeryUnitProductionFormModel;
import org.cyk.system.company.ui.web.primefaces.model.CompanyFormModel;
import org.cyk.system.root.business.api.BusinessEntityInfos;
import org.cyk.ui.api.data.collector.form.AbstractFormModel;
import org.cyk.ui.web.primefaces.page.crud.AbstractCrudOnePage;
import org.cyk.utility.common.annotation.user.interfaces.IncludeInputs;
import org.cyk.utility.common.annotation.user.interfaces.Input;
import org.cyk.utility.common.annotation.user.interfaces.InputChoice;
import org.cyk.utility.common.annotation.user.interfaces.InputOneChoice;
import org.cyk.utility.common.annotation.user.interfaces.InputOneCombo;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class BakeryUnitCrudOnePage extends AbstractCrudOnePage<BakeryUnit> implements Serializable {

	private static final long serialVersionUID = 9040359120893077422L;

	@Override
	protected BusinessEntityInfos fetchBusinessEntityInfos() {
		return uiManager.businessEntityInfos(BakeryUnit.class);
	}
	
	@Override
	protected Class<? extends AbstractFormModel<?>> __formModelClass__() {
		return BakeryUnitFormModel.class;
	}
	
	/**/
	
	public BakeryUnitProductionFormModel formModel(){
		return (BakeryUnitProductionFormModel) form.getData();
	}
	
	/**/
	
	@Getter @Setter
	public static class BakeryUnitFormModel extends AbstractFormModel<BakeryUnit> implements Serializable {

		private static final long serialVersionUID = -9189769754078833773L;
		
		@IncludeInputs
		@NotNull private CompanyFormModel companyFormModel;
		
		@Input @InputChoice @InputOneChoice @InputOneCombo
		@NotNull private EnergyType energyType;
		
		@Override
		public void read() {
			super.read();
			
		}
		
		@Override
		public void write() {
			super.write();
		}
		
	}
				
}