package modeloInfo;

public class InfoServerFuncional implements Informable {
    private String IpServer;
    private int puertoServer;

    @Override
    public int getIdOperacion() {
        return 1;
    }

    public String getIpServer() {
        return IpServer;
    }

    public void setIpServer(String ipServer) {
        IpServer = ipServer;
    }

    public int getPuertoServer() {
        return puertoServer;
    }

    public void setPuertoServer(int puertoServer) {
        this.puertoServer = puertoServer;
    }

}