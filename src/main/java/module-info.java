module com.tugalsan.api.math {
    requires commons.math3;
    requires com.tugalsan.api.union;
    requires com.tugalsan.api.callable;
    requires com.tugalsan.api.list;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.string;
    exports com.tugalsan.api.math.client;
    exports com.tugalsan.api.math.server;
}
