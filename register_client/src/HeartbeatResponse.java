public class HeartbeatResponse {
    private Integer code;
    private String status;

    @Override
    public String toString() {
        return "HeartbeatResponse{" +
                "code=" + code +
                ", status='" + status + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
