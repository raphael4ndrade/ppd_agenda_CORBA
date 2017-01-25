package AgendaCORBA;


/**
* AgendaCORBA/AgendaInterfacePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from AgendaInterface.idl
* Thursday, September 15, 2016 7:49:22 PM BRT
*/

public abstract class AgendaInterfacePOA extends org.omg.PortableServer.Servant
 implements AgendaCORBA.AgendaInterfaceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getListContacts", new java.lang.Integer (0));
    _methods.put ("setListContacts", new java.lang.Integer (1));
    _methods.put ("isAlive", new java.lang.Integer (2));
    _methods.put ("insert", new java.lang.Integer (3));
    _methods.put ("remove", new java.lang.Integer (4));
    _methods.put ("search", new java.lang.Integer (5));
    _methods.put ("update", new java.lang.Integer (6));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // AgendaCORBA/AgendaInterface/getListContacts
       {
         String $result[] = null;
         $result = this.getListContacts ();
         out = $rh.createReply();
         AgendaCORBA.listContactsHelper.write (out, $result);
         break;
       }

       case 1:  // AgendaCORBA/AgendaInterface/setListContacts
       {
         String list[] = AgendaCORBA.listContactsHelper.read (in);
         this.setListContacts (list);
         out = $rh.createReply();
         break;
       }

       case 2:  // AgendaCORBA/AgendaInterface/isAlive
       {
         String $result = null;
         $result = this.isAlive ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // AgendaCORBA/AgendaInterface/insert
       {
         String name = in.read_string ();
         int number = in.read_long ();
         this.insert (name, number);
         out = $rh.createReply();
         break;
       }

       case 4:  // AgendaCORBA/AgendaInterface/remove
       {
         int index = in.read_long ();
         this.remove (index);
         out = $rh.createReply();
         break;
       }

       case 5:  // AgendaCORBA/AgendaInterface/search
       {
         String name = in.read_string ();
         int $result = (int)0;
         $result = this.search (name);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 6:  // AgendaCORBA/AgendaInterface/update
       {
         int index = in.read_long ();
         String name = in.read_string ();
         int number = in.read_long ();
         this.update (index, name, number);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:AgendaCORBA/AgendaInterface:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public AgendaInterface _this() 
  {
    return AgendaInterfaceHelper.narrow(
    super._this_object());
  }

  public AgendaInterface _this(org.omg.CORBA.ORB orb) 
  {
    return AgendaInterfaceHelper.narrow(
    super._this_object(orb));
  }


} // class AgendaInterfacePOA
