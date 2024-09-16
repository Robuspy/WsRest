package com.pablosrl.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

//import org.apache.coyote.http11.upgrade.UpgradeInbound;

public abstract class StreamInbound /*implements UpgradeInbound */{

	protected abstract void onBinaryData(InputStream is) throws IOException;

    protected abstract void onTextData(Reader r) throws IOException;
}
