

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;


public class TT_Entails {
	Set<String> symbol_List = new HashSet<String>();	
//	int cnt = 0;

	public boolean TT_Entails(LogicalExpression knowledge_base, LogicalExpression statement, ModelStructure model) {
		List<String> symbol_List = generate_Symbols(knowledge_base, statement);
		symbol_List = filter_symbols(model,symbol_List);
		return TT_Check_All(knowledge_base, statement, symbol_List, model);
	}

	private List<String> filter_symbols(ModelStructure model, List<String> output_list) {
		Iterator<Entry<String,Boolean>> i = model.hashMap.entrySet().iterator();
		while (i.hasNext()) {	    	
			Entry<String,Boolean> pair = (Entry<String,Boolean>)i.next();
			output_list.remove(pair.getKey());	       
		}
		return output_list;
	}
	
	public boolean TT_Check_All(LogicalExpression kb, LogicalExpression alpha,	List<String> symbols, ModelStructure model) {		
		if (symbols.isEmpty()) {			
			boolean bool_True_Check = pl_True(kb, model);			
			if(bool_True_Check)
				return pl_True(alpha, model);				
			else
				return true;		
		} else {
			String strTemp = (String)symbols.get(0);			
			List<String> remianing_symbol = symbols.subList(1, symbols.size());			
			ModelStructure objTrueModel = model.extend(strTemp, true);
			ModelStructure temp_Model = model.extend(strTemp, false);
			return (TT_Check_All(kb, alpha, remianing_symbol, objTrueModel) && (TT_Check_All(kb, alpha, remianing_symbol, temp_Model)));
		}		
	}
	boolean pl_True(LogicalExpression sentence, ModelStructure model){
		
		Vector<LogicalExpression> symbolList = sentence.getSubexpressions();
		Boolean flag = false;

		if( sentence.getConnective() == null ) {						
			return model.hashMap.get(sentence.getUniqueSymbol());			
		}
//		else {
//			switch (sentence.getConnective()) {
//			case "and":
//				flag = true;
//				for(int i = 0; i < symbolList.size(); i++){				
//					flag = flag && pl_True(symbolList.get(i),model);
//					if(flag==false){	
//						return flag;
//					}
//				}
//				return flag;
//			
//			case "or":
//				for(int i=0;i<symbolList.size();i++){
//					flag = flag || pl_True(symbolList.get(i),model);
//				}
//				return flag;		
//
//			case "not":
//				return !(pl_True(sentence.getNextSubexpression(),model));
//
//			
//			case "xor":
//				int truth_count=0;
//				for(int i = 0; i < symbolList.size(); i++){
//					boolean boolRetrieved = pl_True(symbolList.get(i),model);
//					if(boolRetrieved==true)
//						truth_count++;
//					if(truth_count>1)
//						return false;
//					flag = ((flag||boolRetrieved) && !(flag && boolRetrieved));
//				}
//				return flag;
//				
//			case "if":
//				flag = !(flag && !(pl_True(symbolList.get(1),model)));
//				return flag;	
//			
//			case "iff":
//				return flag == pl_True(symbolList.get(1),model);
//
//			default:
//				
//				break;
//			}
//		}
		else if(sentence.getConnective()!=null && sentence.getConnective().equalsIgnoreCase("and")){			
			flag = true;
			for(int i = 0; i < symbolList.size(); i++){				
				flag = flag && pl_True(symbolList.get(i),model);
				if(flag==false){	
					return flag;
				}
			}
			return flag;
		}
		else if(sentence.getConnective()!=null && sentence.getConnective().equalsIgnoreCase("or")){			
			for(int i=0;i<symbolList.size();i++){
				flag = flag || pl_True(symbolList.get(i),model);
			}
			return flag;		
		}
		else if(sentence.getConnective()!=null && sentence.getConnective().equalsIgnoreCase("not")){			
			return !(pl_True(sentence.getNextSubexpression(),model));
		}
		else if(sentence.getConnective()!=null && sentence.getConnective().equalsIgnoreCase("xor")){			
			int truth_cnt1=0;
			for(int i = 0; i < symbolList.size(); i++){
				boolean retrieved = pl_True(symbolList.get(i),model);
				if(retrieved==true)
					truth_cnt1++;
				if(truth_cnt1>1)
					return false;
				flag = ((flag||retrieved) && !(flag && retrieved));
			}
			return flag;
		}
		else if(sentence.getConnective()!=null && sentence.getConnective().equalsIgnoreCase("if")){		
			flag = !(flag && !(pl_True(symbolList.get(1),model)));
			return flag;			
		}
		else if(sentence.getConnective()!=null && sentence.getConnective().equalsIgnoreCase("iff")){			
			return flag == pl_True(symbolList.get(1),model);
		}
		
		
		return true;
	}

	

	List<String> generate_Symbols(LogicalExpression kb, LogicalExpression alpha){		
		generate_Symbols(kb);
		generate_Symbols(alpha);		
		List<String> list_symbol = new ArrayList<String>(symbol_List);
		return list_symbol;
	}

	void generate_Symbols(LogicalExpression logical_Ex){
		if(!(logical_Ex.getUniqueSymbol() == null))
			symbol_List.add(logical_Ex.getUniqueSymbol());
		else {
			for(int i = 0 ; i < logical_Ex.getSubexpressions().size(); i++ ){
				LogicalExpression logical_Exx = logical_Ex.getSubexpressions().get(i);
				generate_Symbols(logical_Exx);
				if(!(logical_Exx.getUniqueSymbol() == null))
					symbol_List.add(logical_Exx.getUniqueSymbol());			
			}
		}
	}		

	class ModelStructure{
		public HashMap<String,Boolean> hashMap = new HashMap<String,Boolean>();
		public ModelStructure extend(String symbol, boolean b) {
			ModelStructure objModel = new ModelStructure();
			objModel.hashMap.putAll(this.hashMap);
			objModel.hashMap.put(symbol, b);
			return objModel;
		}
	}
}
