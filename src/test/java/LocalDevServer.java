import com.stackmob.core.jar.JarEntryObject;
import com.stackmob.customcode.dev.server.CustomCodeServer;
import com.trivayh.server.EntryPointExtender;

public class LocalDevServer {
    public static void main(String[] args) {
        JarEntryObject entryObject = new EntryPointExtender();

        CustomCodeServer.serve(entryObject, "13ac0142-9e05-4457-97f6-bde9b207e1a0", "c41db33b-0a95-46ac-8083-09be60df81bb", 8080);
    }
}