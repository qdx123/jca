/**********************************************************************
 *
 *      Original Author: Eric Boucher
 *      Date:            05/05/2003
 *
 *      Experimental Physics and Industrial Control System (EPICS)
 *
 *      Copyright 1991, the University of Chicago Board of Governors.
 *
 *      This software was produced under  U.S. Government contract
 *      W-31-109-ENG-38 at Argonne National Laboratory.
 *
 *      Beamline Controls & Data Acquisition Group
 *      Experimental Facilities Division
 *      Advanced Photon Source
 *      Argonne National Laboratory
 *
 *
 * $Id: DBR_PutAckT.java,v 1.2 2006-08-16 15:21:51 msekoranja Exp $
 *
 * Modification Log:
 * 01. 05/07/2003  erb  initial development
 *
 */

package gov.aps.jca.dbr;

import gov.aps.jca.CAStatus;
import gov.aps.jca.CAStatusException;

public class DBR_PutAckT extends DBR implements SHORT {
  public static final DBRType TYPE= new DBRType("DBR_PUT_ACKT", 35, DBR_PutAckT.class);
  
  
  public DBR_PutAckT() {

    this( 1 );

  }

  public DBR_PutAckT( int count ) {

    this( new short[count] );

  }

  public DBR_PutAckT( short[] value ) {
    super( value );
  }

  public DBRType getType() {
    return TYPE;
  }


  public short[] getShortValue() {
    return( short[] )getValue();
  }

	/**
	 * @see gov.aps.jca.dbr.DBR#convert(gov.aps.jca.dbr.DBRType)
	 */
	public DBR convert(DBRType convertType) throws CAStatusException {
		
		// no conversion needed
		if (convertType.isSHORT())
			return this;

		final short[] fromValue = getShortValue();
		DBR dbr = DBRFactory.create(convertType, _count);
		
		if (convertType.isSTRING())
		{
			String[] toValue = ((DBR_String)dbr).getStringValue();
			for (int i = 0; i < _count; i++)
				toValue[i] = String.valueOf(fromValue[i]);
		} /*
		else if (convertType.isSHORT())
		{
			short[] toValue = ((DBR_Short)dbr).getShortValue();
			for (int i = 0; i < _count; i++)
				toValue[i] = (short)fromValue[i];
		} */
		else if (convertType.isFLOAT())
		{
			float[] toValue = ((DBR_Float)dbr).getFloatValue();
			for (int i = 0; i < _count; i++)
				toValue[i] = (float)fromValue[i];
		}
		else if (convertType.isENUM())
		{
			short[] toValue = ((DBR_Enum)dbr).getEnumValue();
			for (int i = 0; i < _count; i++)
				toValue[i] = (short)fromValue[i];
		} 
		else if (convertType.isBYTE())
		{
			byte[] toValue = ((DBR_Byte)dbr).getByteValue();
			for (int i = 0; i < _count; i++)
				toValue[i] = (byte)fromValue[i];
		} 
		else if (convertType.isINT())
		{
			int[] toValue = ((DBR_Int)dbr).getIntValue();
			for (int i = 0; i < _count; i++)
				toValue[i] = (int)fromValue[i];
		}
		else if (convertType.isDOUBLE())
		{
			double[] toValue = ((DBR_Double)dbr).getDoubleValue();
			for (int i = 0; i < _count; i++)
				toValue[i] = (double)fromValue[i];
		}
		else
		{
			throw new CAStatusException(CAStatus.NOCONVERT, "converstion not supported");
		}
		
		return dbr;
	}

}
