package ast.sentencias;

import ast.main.ASTNodeDefault;

public abstract class AbstractStatement extends ASTNodeDefault implements Statement{

	private boolean terminable;
	
	public AbstractStatement(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean getTerminable() {
		return terminable;
	}

	@Override
	public void setTerminable(boolean bool) {
		terminable = bool;
	}

}
