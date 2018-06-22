package ast.main;

public abstract class ASTNodeDefault implements ASTNode {

	private int line;
	private int column;

	public ASTNodeDefault(int line, int column) {
		super();
		this.line = line;
		this.column = column;
	}

	@Override
	public int getLine() {
		return line;
	}

	@Override
	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return "ASTNodeDefault [line=" + line + ", column=" + column + "]";
	}

	public void setLine(int line) {
		this.line = line;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + line;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ASTNodeDefault other = (ASTNodeDefault) obj;
		if (column != other.column)
			return false;
		if (line != other.line)
			return false;
		return true;
	}

}
