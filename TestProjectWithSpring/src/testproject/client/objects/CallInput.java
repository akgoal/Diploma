package testproject.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CallInput implements Serializable {

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
