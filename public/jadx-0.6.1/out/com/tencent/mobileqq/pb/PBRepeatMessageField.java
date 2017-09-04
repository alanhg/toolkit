package com.tencent.mobileqq.pb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class PBRepeatMessageField<T extends MessageMicro<T>> extends PBField<List<T>> {
    private final Class<T> helper;
    private List<T> value = Collections.emptyList();

    public PBRepeatMessageField(Class<T> cls) {
        this.helper = cls;
    }

    public List<T> get() {
        if (this.value == Collections.emptyList()) {
            this.value = new ArrayList();
        }
        return this.value;
    }

    public T get(int i) {
        return (MessageMicro) this.value.get(i);
    }

    public void set(List<T> list) {
        this.value = list;
    }

    public void set(int i, T t) {
        this.value.set(i, t);
    }

    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    public boolean has() {
        return isEmpty();
    }

    public int size() {
        return this.value.size();
    }

    public void add(T t) {
        get().add(t);
    }

    public void addAll(Collection<T> collection) {
        get().addAll(collection);
    }

    public void remove(int i) {
        get().remove(i);
    }

    public int computeSize(int i) {
        return computeSizeDirectly(i, this.value);
    }

    protected int computeSizeDirectly(int i, List<T> list) {
        int i2 = 0;
        for (T t : list) {
            i2 = t.computeSizeDirectly(i, (MessageMicro) t) + i2;
        }
        return i2;
    }

    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro, int i) throws IOException {
        writeToDirectly(codedOutputStreamMicro, i, this.value);
    }

    protected void writeToDirectly(CodedOutputStreamMicro codedOutputStreamMicro, int i, List<T> list) throws IOException {
        for (T t : list) {
            t.writeToDirectly(codedOutputStreamMicro, i, (MessageMicro) t);
        }
    }

    public void readFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        try {
            MessageMicro messageMicro = (MessageMicro) this.helper.newInstance();
            codedInputStreamMicro.readMessage(messageMicro);
            add(messageMicro);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    protected List<T> readFromDirectly(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        throw new RuntimeException("PBRepeatField not support readFromDirectly method.");
    }

    public void clear(Object obj) {
        this.value = Collections.emptyList();
    }

    protected void copyFrom(PBField<List<T>> pBField) {
        PBRepeatMessageField pBRepeatMessageField = (PBRepeatMessageField) pBField;
        if (pBRepeatMessageField.isEmpty()) {
            this.value = Collections.emptyList();
            return;
        }
        int i;
        List list = get();
        Class cls = pBRepeatMessageField.get(0).getClass();
        int size = pBRepeatMessageField.value.size() - list.size();
        if (size > 0) {
            for (i = 0; i < size; i++) {
                try {
                    list.add((MessageMicro) cls.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
        } else if (size < 0) {
            list.subList(-size, list.size()).clear();
        }
        for (i = 0; i < list.size(); i++) {
            ((MessageMicro) list.get(i)).copyFrom((MessageMicro) pBRepeatMessageField.value.get(i));
        }
    }
}
