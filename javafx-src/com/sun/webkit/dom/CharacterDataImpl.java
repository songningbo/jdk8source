/*
 * Copyright (c) 2013, 2017, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
*/

package com.sun.webkit.dom;

import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class CharacterDataImpl extends NodeImpl implements CharacterData {
    CharacterDataImpl(long peer) {
        super(peer);
    }

    static Node getImpl(long peer) {
        return (Node)create(peer);
    }


// Attributes
    public String getData() {
        return getDataImpl(getPeer());
    }
    native static String getDataImpl(long peer);

    public void setData(String value) {
        setDataImpl(getPeer(), value);
    }
    native static void setDataImpl(long peer, String value);

    public int getLength() {
        return getLengthImpl(getPeer());
    }
    native static int getLengthImpl(long peer);

    public Element getPreviousElementSibling() {
        return ElementImpl.getImpl(getPreviousElementSiblingImpl(getPeer()));
    }
    native static long getPreviousElementSiblingImpl(long peer);

    public Element getNextElementSibling() {
        return ElementImpl.getImpl(getNextElementSiblingImpl(getPeer()));
    }
    native static long getNextElementSiblingImpl(long peer);


// Functions
    public String substringData(int offset
        , int length) throws DOMException
    {
        return substringDataImpl(getPeer()
            , offset
            , length);
    }
    native static String substringDataImpl(long peer
        , int offset
        , int length);


    public void appendData(String data)
    {
        appendDataImpl(getPeer()
            , data);
    }
    native static void appendDataImpl(long peer
        , String data);


    public void insertData(int offset
        , String data) throws DOMException
    {
        insertDataImpl(getPeer()
            , offset
            , data);
    }
    native static void insertDataImpl(long peer
        , int offset
        , String data);


    public void deleteData(int offset
        , int length) throws DOMException
    {
        deleteDataImpl(getPeer()
            , offset
            , length);
    }
    native static void deleteDataImpl(long peer
        , int offset
        , int length);


    public void replaceData(int offset
        , int length
        , String data) throws DOMException
    {
        replaceDataImpl(getPeer()
            , offset
            , length
            , data);
    }
    native static void replaceDataImpl(long peer
        , int offset
        , int length
        , String data);


    public void remove() throws DOMException
    {
        removeImpl(getPeer());
    }
    native static void removeImpl(long peer);


}

