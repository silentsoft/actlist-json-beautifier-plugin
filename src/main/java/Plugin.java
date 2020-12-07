

import java.net.URI;

import org.silentsoft.actlist.plugin.ActlistPlugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Plugin extends ActlistPlugin {
	
	@FXML
	private TextArea leftArea, rightArea;
	
	public static void main(String[] args) {
		debug();
	}
	
	public Plugin() throws Exception {
		super("JSON Beautifier");
		
		setPluginVersion("1.0.0");
		setPluginAuthor("silentsoft.org", URI.create("https://github.com/silentsoft/actlist-json-beautifier-plugin"));
		setPluginUpdateCheckURI(URI.create("http://actlist.silentsoft.org/api/plugin/0db46653/update/check"));
		setPluginDescription(URI.create("https://github.com/silentsoft/actlist-json-beautifier-plugin/blob/master/README.md"));
		setPluginChangeLog(URI.create("https://github.com/silentsoft/actlist-json-beautifier-plugin/blob/master/CHANGELOG.md"));
		setPluginLicense(URI.create("https://github.com/silentsoft/actlist-json-beautifier-plugin/blob/master/NOTICE.md"));
		
		setMinimumCompatibleVersion(1, 2, 6);
	}

	@Override
	protected void initialize() throws Exception {
		leftArea.setOnKeyReleased(keyEvent -> {
			beautify(leftArea, rightArea, true);
		});
		rightArea.setOnKeyReleased(keyEvent -> {
			beautify(rightArea, leftArea, false);
		});
	}

	@Override
	public void pluginActivated() throws Exception {
		
	}

	@Override
	public void pluginDeactivated() throws Exception {
		
	}
	
	private void beautify(TextArea source, TextArea target, boolean shouldPrettyPrint) {
		if (source.getText() == null || source.getText().length() <= 0) {
			return;
		}
		
		String value = null;
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			PrettyPrinter prettyPrinter = shouldPrettyPrint ? new DefaultPrettyPrinter() : new MinimalPrettyPrinter();
			value = objectMapper.writer(prettyPrinter).writeValueAsString(objectMapper.readTree(source.getText()));
		} catch (JsonProcessingException e) {
			target.setText("# JSON Processing Error #");
		}
		
		if (value != null) {
			target.setText(value);
		}
	} 
	
}
