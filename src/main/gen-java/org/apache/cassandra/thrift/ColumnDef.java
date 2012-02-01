/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package org.apache.cassandra.thrift;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.*;
import org.apache.thrift.async.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.transport.*;
import org.apache.thrift.protocol.*;

// No additional import required for struct/union.

public class ColumnDef implements TBase<ColumnDef, ColumnDef._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("ColumnDef");

  private static final TField NAME_FIELD_DESC = new TField("name", TType.STRING, (short)1);
  private static final TField VALIDATION_CLASS_FIELD_DESC = new TField("validation_class", TType.STRING, (short)2);
  private static final TField INDEX_TYPE_FIELD_DESC = new TField("index_type", TType.I32, (short)3);
  private static final TField INDEX_NAME_FIELD_DESC = new TField("index_name", TType.STRING, (short)4);
  private static final TField INDEX_OPTIONS_FIELD_DESC = new TField("index_options", TType.MAP, (short)5);

  public ByteBuffer name;
  public String validation_class;
  /**
   * 
   * @see IndexType
   */
  public IndexType index_type;
  public String index_name;
  public Map<String,String> index_options;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    NAME((short)1, "name"),
    VALIDATION_CLASS((short)2, "validation_class"),
    /**
     * 
     * @see IndexType
     */
    INDEX_TYPE((short)3, "index_type"),
    INDEX_NAME((short)4, "index_name"),
    INDEX_OPTIONS((short)5, "index_options");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NAME
          return NAME;
        case 2: // VALIDATION_CLASS
          return VALIDATION_CLASS;
        case 3: // INDEX_TYPE
          return INDEX_TYPE;
        case 4: // INDEX_NAME
          return INDEX_NAME;
        case 5: // INDEX_OPTIONS
          return INDEX_OPTIONS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME, new FieldMetaData("name", TFieldRequirementType.REQUIRED, 
        new FieldValueMetaData(TType.STRING)));
    tmpMap.put(_Fields.VALIDATION_CLASS, new FieldMetaData("validation_class", TFieldRequirementType.REQUIRED, 
        new FieldValueMetaData(TType.STRING)));
    tmpMap.put(_Fields.INDEX_TYPE, new FieldMetaData("index_type", TFieldRequirementType.OPTIONAL, 
        new EnumMetaData(TType.ENUM, IndexType.class)));
    tmpMap.put(_Fields.INDEX_NAME, new FieldMetaData("index_name", TFieldRequirementType.OPTIONAL, 
        new FieldValueMetaData(TType.STRING)));
    tmpMap.put(_Fields.INDEX_OPTIONS, new FieldMetaData("index_options", TFieldRequirementType.OPTIONAL, 
        new MapMetaData(TType.MAP, 
            new FieldValueMetaData(TType.STRING), 
            new FieldValueMetaData(TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(ColumnDef.class, metaDataMap);
  }

  public ColumnDef() {
  }

  public ColumnDef(
    ByteBuffer name,
    String validation_class)
  {
    this();
    this.name = name;
    this.validation_class = validation_class;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ColumnDef(ColumnDef other) {
    if (other.isSetName()) {
      this.name = TBaseHelper.copyBinary(other.name);
;
    }
    if (other.isSetValidation_class()) {
      this.validation_class = other.validation_class;
    }
    if (other.isSetIndex_type()) {
      this.index_type = other.index_type;
    }
    if (other.isSetIndex_name()) {
      this.index_name = other.index_name;
    }
    if (other.isSetIndex_options()) {
      Map<String,String> __this__index_options = new HashMap<String,String>();
      for (Map.Entry<String, String> other_element : other.index_options.entrySet()) {

        String other_element_key = other_element.getKey();
        String other_element_value = other_element.getValue();

        String __this__index_options_copy_key = other_element_key;

        String __this__index_options_copy_value = other_element_value;

        __this__index_options.put(__this__index_options_copy_key, __this__index_options_copy_value);
      }
      this.index_options = __this__index_options;
    }
  }

  public ColumnDef deepCopy() {
    return new ColumnDef(this);
  }

  @Override
  public void clear() {
    this.name = null;
    this.validation_class = null;
    this.index_type = null;
    this.index_name = null;
    this.index_options = null;
  }

  public byte[] getName() {
    setName(TBaseHelper.rightSize(name));
    return name.array();
  }

  public ByteBuffer BufferForName() {
    return name;
  }

  public ColumnDef setName(byte[] name) {
    setName(ByteBuffer.wrap(name));
    return this;
  }

  public ColumnDef setName(ByteBuffer name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been asigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public String getValidation_class() {
    return this.validation_class;
  }

  public ColumnDef setValidation_class(String validation_class) {
    this.validation_class = validation_class;
    return this;
  }

  public void unsetValidation_class() {
    this.validation_class = null;
  }

  /** Returns true if field validation_class is set (has been asigned a value) and false otherwise */
  public boolean isSetValidation_class() {
    return this.validation_class != null;
  }

  public void setValidation_classIsSet(boolean value) {
    if (!value) {
      this.validation_class = null;
    }
  }

  /**
   * 
   * @see IndexType
   */
  public IndexType getIndex_type() {
    return this.index_type;
  }

  /**
   * 
   * @see IndexType
   */
  public ColumnDef setIndex_type(IndexType index_type) {
    this.index_type = index_type;
    return this;
  }

  public void unsetIndex_type() {
    this.index_type = null;
  }

  /** Returns true if field index_type is set (has been asigned a value) and false otherwise */
  public boolean isSetIndex_type() {
    return this.index_type != null;
  }

  public void setIndex_typeIsSet(boolean value) {
    if (!value) {
      this.index_type = null;
    }
  }

  public String getIndex_name() {
    return this.index_name;
  }

  public ColumnDef setIndex_name(String index_name) {
    this.index_name = index_name;
    return this;
  }

  public void unsetIndex_name() {
    this.index_name = null;
  }

  /** Returns true if field index_name is set (has been asigned a value) and false otherwise */
  public boolean isSetIndex_name() {
    return this.index_name != null;
  }

  public void setIndex_nameIsSet(boolean value) {
    if (!value) {
      this.index_name = null;
    }
  }

  public int getIndex_optionsSize() {
    return (this.index_options == null) ? 0 : this.index_options.size();
  }

  public void putToIndex_options(String key, String val) {
    if (this.index_options == null) {
      this.index_options = new HashMap<String,String>();
    }
    this.index_options.put(key, val);
  }

  public Map<String,String> getIndex_options() {
    return this.index_options;
  }

  public ColumnDef setIndex_options(Map<String,String> index_options) {
    this.index_options = index_options;
    return this;
  }

  public void unsetIndex_options() {
    this.index_options = null;
  }

  /** Returns true if field index_options is set (has been asigned a value) and false otherwise */
  public boolean isSetIndex_options() {
    return this.index_options != null;
  }

  public void setIndex_optionsIsSet(boolean value) {
    if (!value) {
      this.index_options = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((ByteBuffer)value);
      }
      break;

    case VALIDATION_CLASS:
      if (value == null) {
        unsetValidation_class();
      } else {
        setValidation_class((String)value);
      }
      break;

    case INDEX_TYPE:
      if (value == null) {
        unsetIndex_type();
      } else {
        setIndex_type((IndexType)value);
      }
      break;

    case INDEX_NAME:
      if (value == null) {
        unsetIndex_name();
      } else {
        setIndex_name((String)value);
      }
      break;

    case INDEX_OPTIONS:
      if (value == null) {
        unsetIndex_options();
      } else {
        setIndex_options((Map<String,String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME:
      return getName();

    case VALIDATION_CLASS:
      return getValidation_class();

    case INDEX_TYPE:
      return getIndex_type();

    case INDEX_NAME:
      return getIndex_name();

    case INDEX_OPTIONS:
      return getIndex_options();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NAME:
      return isSetName();
    case VALIDATION_CLASS:
      return isSetValidation_class();
    case INDEX_TYPE:
      return isSetIndex_type();
    case INDEX_NAME:
      return isSetIndex_name();
    case INDEX_OPTIONS:
      return isSetIndex_options();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ColumnDef)
      return this.equals((ColumnDef)that);
    return false;
  }

  public boolean equals(ColumnDef that) {
    if (that == null)
      return false;

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_validation_class = true && this.isSetValidation_class();
    boolean that_present_validation_class = true && that.isSetValidation_class();
    if (this_present_validation_class || that_present_validation_class) {
      if (!(this_present_validation_class && that_present_validation_class))
        return false;
      if (!this.validation_class.equals(that.validation_class))
        return false;
    }

    boolean this_present_index_type = true && this.isSetIndex_type();
    boolean that_present_index_type = true && that.isSetIndex_type();
    if (this_present_index_type || that_present_index_type) {
      if (!(this_present_index_type && that_present_index_type))
        return false;
      if (!this.index_type.equals(that.index_type))
        return false;
    }

    boolean this_present_index_name = true && this.isSetIndex_name();
    boolean that_present_index_name = true && that.isSetIndex_name();
    if (this_present_index_name || that_present_index_name) {
      if (!(this_present_index_name && that_present_index_name))
        return false;
      if (!this.index_name.equals(that.index_name))
        return false;
    }

    boolean this_present_index_options = true && this.isSetIndex_options();
    boolean that_present_index_options = true && that.isSetIndex_options();
    if (this_present_index_options || that_present_index_options) {
      if (!(this_present_index_options && that_present_index_options))
        return false;
      if (!this.index_options.equals(that.index_options))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(ColumnDef other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    ColumnDef typedOther = (ColumnDef)other;

    lastComparison = Boolean.valueOf(isSetName()).compareTo(typedOther.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = TBaseHelper.compareTo(this.name, typedOther.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetValidation_class()).compareTo(typedOther.isSetValidation_class());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValidation_class()) {
      lastComparison = TBaseHelper.compareTo(this.validation_class, typedOther.validation_class);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIndex_type()).compareTo(typedOther.isSetIndex_type());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIndex_type()) {
      lastComparison = TBaseHelper.compareTo(this.index_type, typedOther.index_type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIndex_name()).compareTo(typedOther.isSetIndex_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIndex_name()) {
      lastComparison = TBaseHelper.compareTo(this.index_name, typedOther.index_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIndex_options()).compareTo(typedOther.isSetIndex_options());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIndex_options()) {
      lastComparison = TBaseHelper.compareTo(this.index_options, typedOther.index_options);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // NAME
          if (field.type == TType.STRING) {
            this.name = iprot.readBinary();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // VALIDATION_CLASS
          if (field.type == TType.STRING) {
            this.validation_class = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // INDEX_TYPE
          if (field.type == TType.I32) {
            this.index_type = IndexType.findByValue(iprot.readI32());
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 4: // INDEX_NAME
          if (field.type == TType.STRING) {
            this.index_name = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 5: // INDEX_OPTIONS
          if (field.type == TType.MAP) {
            {
              TMap _map41 = iprot.readMapBegin();
              this.index_options = new HashMap<String,String>(2*_map41.size);
              for (int _i42 = 0; _i42 < _map41.size; ++_i42)
              {
                String _key43;
                String _val44;
                _key43 = iprot.readString();
                _val44 = iprot.readString();
                this.index_options.put(_key43, _val44);
              }
              iprot.readMapEnd();
            }
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.name != null) {
      oprot.writeFieldBegin(NAME_FIELD_DESC);
      oprot.writeBinary(this.name);
      oprot.writeFieldEnd();
    }
    if (this.validation_class != null) {
      oprot.writeFieldBegin(VALIDATION_CLASS_FIELD_DESC);
      oprot.writeString(this.validation_class);
      oprot.writeFieldEnd();
    }
    if (this.index_type != null) {
      if (isSetIndex_type()) {
        oprot.writeFieldBegin(INDEX_TYPE_FIELD_DESC);
        oprot.writeI32(this.index_type.getValue());
        oprot.writeFieldEnd();
      }
    }
    if (this.index_name != null) {
      if (isSetIndex_name()) {
        oprot.writeFieldBegin(INDEX_NAME_FIELD_DESC);
        oprot.writeString(this.index_name);
        oprot.writeFieldEnd();
      }
    }
    if (this.index_options != null) {
      if (isSetIndex_options()) {
        oprot.writeFieldBegin(INDEX_OPTIONS_FIELD_DESC);
        {
          oprot.writeMapBegin(new TMap(TType.STRING, TType.STRING, this.index_options.size()));
          for (Map.Entry<String, String> _iter45 : this.index_options.entrySet())
          {
            oprot.writeString(_iter45.getKey());
            oprot.writeString(_iter45.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ColumnDef(");
    boolean first = true;

    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      TBaseHelper.toString(this.name, sb);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("validation_class:");
    if (this.validation_class == null) {
      sb.append("null");
    } else {
      sb.append(this.validation_class);
    }
    first = false;
    if (isSetIndex_type()) {
      if (!first) sb.append(", ");
      sb.append("index_type:");
      if (this.index_type == null) {
        sb.append("null");
      } else {
        sb.append(this.index_type);
      }
      first = false;
    }
    if (isSetIndex_name()) {
      if (!first) sb.append(", ");
      sb.append("index_name:");
      if (this.index_name == null) {
        sb.append("null");
      } else {
        sb.append(this.index_name);
      }
      first = false;
    }
    if (isSetIndex_options()) {
      if (!first) sb.append(", ");
      sb.append("index_options:");
      if (this.index_options == null) {
        sb.append("null");
      } else {
        sb.append(this.index_options);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    if (name == null) {
      throw new TProtocolException("Required field 'name' was not present! Struct: " + toString());
    }
    if (validation_class == null) {
      throw new TProtocolException("Required field 'validation_class' was not present! Struct: " + toString());
    }
  }

}

