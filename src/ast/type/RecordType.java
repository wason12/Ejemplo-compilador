package ast.type;

import java.util.List;

import visitor.Visitor;

public class RecordType extends AbstractType{

	private List<RecordField> campos;

	public RecordType(int line, int column, List<RecordField> campos) {
		super(line, column);
		this.campos = campos;
	}

	public List<RecordField> getCampos() {
		return campos;
	}

	public void setCampos(List<RecordField> campos) {
		this.campos = campos;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("struct {");
		for(RecordField re : campos)
			str.append("\n\t" + re.toString());
		str.append("\n}");
		
		return str.toString();
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((campos == null) ? 0 : campos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecordType other = (RecordType) obj;
		if(other.campos.size() != this.campos.size())
			return false;
		for(int i = 0 ; i<campos.size() ; i++)
			if(!campos.get(i).equals2(other.campos.get(i)))
				return false;
		return true;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
	@Override
	public Type dot(String right) {
		for(RecordField rf : campos) 
			if(rf.getName().equals(right))
				return rf.getType();
		
		return null;
	}
	
	@Override
	public int getNumberOfBytes() {
		int bytes = 0;
		
		for(RecordField rf : campos) {
			bytes += rf.getType().getNumberOfBytes();
		}
		
		return bytes;
	}

	public RecordField get(String name) {
		for(RecordField rf : campos)
			if(rf.getName().equals(name))
				return rf;
		
		return null;
	}
	
	@Override
	public String toComment() {
		String fields = "";
		for(int i = 0 ; i<campos.size() ; i++) {
			RecordField rf = campos.get(i);
			fields += String.format("Field:[name:%s, type:%s offset:%d]", rf.getName(), rf.getType().toComment(), rf.getOffset());
			if(i < campos.size()-1)
				fields += ", ";
		}
		String comment = String.format("RecordType[fields:[%s]]", fields);
		
		return comment;
	}
}
