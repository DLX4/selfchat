package pers.dlx.selfchat.constants;

public enum MessageType {
    IMAGE("图片"),
    TEXT("文本");

    public String name;

    MessageType(String name) {
        this.name = name;
    }
}
