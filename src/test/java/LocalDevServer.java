import com.stackmob.core.jar.JarEntryObject;
import com.stackmob.customcode.dev.server.CustomCodeServer;
import com.trivayh.server.EntryPointExtender;

public class LocalDevServer {
    public static void main(String[] args) {
        JarEntryObject entryObject = new EntryPointExtender();

        CustomCodeServer.serve(entryObject, "976c2126-2164-4b66-b361-3ffab1554bae", "1de29cc0-dee2-4e53-88d5-720a6b5e1296", 8080);
    }
}