package pers.dlx.selfchat.model;

import lombok.Data;

/**
 * 字典条目
 *
 * @author dinglingxiang
 */
@Data
public class DictEntry<K, V> {

    private final K code;
    private final V value;

    public DictEntry(K code, V value) {
        this.code = code;
        this.value = value;
    }

}
