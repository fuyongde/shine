package com.shine.leaf.api.service;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/19 22:39
 * @desc 生成
 */
public interface SequenceService {

    long nextLong();

    String nextString();

    String nextUUID();

}
