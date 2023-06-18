package team.sdn.entiey;

import lombok.Data;

@Data
public class Param {
    private String duration;
    private String protocolType;
    private String service;
    private String flag;
    private String srcBytes;
    private String dstBytes;
    private String land;
    private String wrongFragment;
    private String urgent;
    private String hot;
    private String numFailedLogins;
    private String loggedIn;
    private String numCompromised;
    private String rootShell;
    private String suAttempted;
    private String numRoot;
    private String numFileCreations;
    private String numShells;
    private String numAccessFiles;
    private String numOutboundCmds;
    private String isHotLogins;
    private String isGuestLogin;
    private String count;
    private String srvCount;
    private String serrorRate;
    private String srvSerrorRate;
    private String rerrorRate;
    private String srvRerrorRate;
    private String sameSrvRate;
    private String diffSrvRate;
    private String srvDiffHostRate;
    private String dstHostCount;
    private String dstHostSrvCount;
    private String dstHostSameSrvRate;
    private String dstHostDiffSrvRate;
    private String dstHostSameSrcPortRate;
    private String dstHostSrvDiffHostRate;
    private String dstHostSerrorRate;
    private String dstHostSrvSerrorRate;
    private String dstHostRerrorRate;
    private String dstHostSrvRerrorRate;

    @Override
    public String toString() {
        return duration + "," +
                protocolType + "," +
                service + "," +
                flag + "," +
                srcBytes + "," +
                dstBytes + "," +
                land + "," +
                wrongFragment + "," +
                urgent + "," +
                hot + "," +
                numFailedLogins + "," +
                loggedIn + "," +
                numCompromised + "," +
                rootShell + "," +
                suAttempted + "," +
                numRoot + "," +
                numFileCreations + "," +
                numShells + "," +
                numAccessFiles + "," +
                numOutboundCmds + "," +
                isHotLogins + "," +
                isGuestLogin + "," +
                count + "," +
                srvCount + "," +
                serrorRate + "," +
                srvSerrorRate + "," +
                rerrorRate + "," +
                srvRerrorRate + "," +
                sameSrvRate + "," +
                diffSrvRate + "," +
                srvDiffHostRate + "," +
                dstHostCount + "," +
                dstHostSrvCount + "," +
                dstHostSameSrvRate + "," +
                dstHostDiffSrvRate + "," +
                dstHostSameSrcPortRate + "," +
                dstHostSrvDiffHostRate + "," +
                dstHostSerrorRate + "," +
                dstHostSrvSerrorRate + "," +
                dstHostRerrorRate + "," +
                dstHostSrvRerrorRate;
    }
}
