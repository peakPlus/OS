package com.company.dynamicPartition;

public class Partition {
    
    private String name;

    private int size;

    private int startAddress;

    private boolean status;

    public Partition(String name, int size, int startAddress, boolean status) {

    	this.name = name;

        this.size = size;

        this.startAddress = startAddress;

        this.status = status;

    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
	public String toString() {
		return "Partition [name=" + name + ", size=" + size + ", startAddress=" + startAddress
				+ ", status=" + status + "]";
	}

	public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
