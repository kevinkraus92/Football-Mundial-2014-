package model.board;

import java.io.Serializable;

import model.element.Lateral;
import model.kicker.Kicker;
public class Cell implements Serializable {
	
	private Content content;
	private Content fixContent;
		
	public void setContent(Content c) {
		this.content = c;
	}
	
	public boolean hasContent() {
		return content != null;
	}
	
	public Content getContent() {
		return content;
	}
	
	public Content removeContent() {
		Content aux = this.content;
		this.content = null;
		return aux;
	}
	
	public boolean canWalkOver() {
		return content == null || content.canWalkOver();
	}
	
	public void onWalk(Kicker player){
		if (content != null) {
			content = content.interact(player);
		} else {
			content = player;
		}
	}
	
	
	public boolean canInteract() {
		return content != null;
	}
	
	public void interact(Kicker player) {
		content = content.interact(player);
	}

	public void setFixContent(Content lateral) {
		this.fixContent=lateral;		
	}

	public boolean hasFixContent() {
		return fixContent!=null;
	}

	public Content getFixContent() {
		return fixContent;
	}
}
