package uqac.gestionvieetu.Sorties;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uqac.gestionvieetu.R;

/**
 * Created by guill on 23/04/2017.
 */

public class FragmentMapActivity extends android.support.v4.app.Fragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, LocationListener {

    private View rootView;
    MapView mapView;
    GoogleMap map;
    private ArrayList<LatLng> listOfPoints=new ArrayList<LatLng>();
    private ArrayList<Marker> listOfMarkers=new ArrayList<Marker>();
    Location mLastLocation;
    boolean mLocationPermissionGranted = false;
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.layout_carte, container, false);
        Button button = (Button) rootView.findViewById(R.id.delmarker);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SuppressionMarqueur();
            }
        });

        MapsInitializer.initialize(this.getActivity());
        mapView = (MapView) rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);
        return rootView;

    }

    @Override
    public void onMapReady(final GoogleMap map)
    {
        LatLng mnPoint = new LatLng(48.419898, -71.052187);
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }


        float zoomLevel = 16;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mnPoint, zoomLevel));
        /*Marker uniMarker = map.addMarker(new MarkerOptions()
                .position(mnPoint)
                .title("Université du Québec à Chicoutimi"));

        listOfMarkers.add(uniMarker);
        listOfPoints.add(mnPoint);*/

        try {
            FileInputStream input = this.getContext().openFileInput("latlngpoints5.txt"); //récupération des marqueurs
            DataInputStream din = new DataInputStream(input);
            int sz = din.readInt(); // Read line count
            for (int i = 0; i < sz; i++) {
                String str = din.readUTF();
                String[] stringArray = str.split(",");
                double latitude = Double.parseDouble(stringArray[0]);
                double longitude = Double.parseDouble(stringArray[1]);
                listOfPoints.add(new LatLng(latitude, longitude));
            }
            din.close();
            for (LatLng point : listOfPoints) {
                Marker sMarker = map.addMarker(new MarkerOptions()
                        .position(point)
                        .icon(BitmapDescriptorFactory.defaultMarker(new Random().nextInt(360)))
                        .draggable(true));
                listOfMarkers.add(sMarker);
                Geocoder geoSave = new Geocoder(getContext());
                List<Address> addresses = null;
                try {
                    addresses = geoSave.getFromLocation(point.latitude, point.longitude, 1);
                    if (addresses.isEmpty()) {
                        sMarker.setTitle("Waiting for Location");
                    }
                    else {
                        if (addresses.size() > 0) {
                            sMarker.setTitle(addresses.get(0).getAddressLine(0) +" "  + addresses.get(0).getPostalCode() +" " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }


        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                Marker nMarker = map.addMarker(new MarkerOptions()
                        .position(point)
                        .icon(BitmapDescriptorFactory.defaultMarker(new Random().nextInt(360)))
                        .draggable(true)
                );
                listOfPoints.add(nMarker.getPosition());
                listOfMarkers.add(nMarker);
                Geocoder geo = new Geocoder(getContext());
                List<Address> addresses = null;
                try {
                    addresses = geo.getFromLocation(point.latitude, point.longitude, 1);
                    if (addresses.isEmpty()) {
                        nMarker.setTitle("Waiting for Location");
                    }
                    else {
                        if (addresses.size() > 0) {
                            nMarker.setTitle(addresses.get(0).getAddressLine(0) +" "  + addresses.get(0).getPostalCode() +" "
                                    + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
                            Toast.makeText(getContext(),"Marqueur ajouté",Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                nMarker.showInfoWindow();
            }
        });
        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                listOfMarkers.remove(marker);
                Geocoder geoSave = new Geocoder(getContext());
                List<Address> addresses = null;
                try {
                    addresses = geoSave.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 1);
                    if (addresses.isEmpty()) {
                        marker.setTitle("Waiting for Location");
                    }
                    else {
                        if (addresses.size() > 0) {
                            marker.setTitle(addresses.get(0).getAddressLine(0) +" "  + addresses.get(0).getPostalCode() +" " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
                        }
                    }

                    listOfPoints.clear();
                    listOfPoints.add(marker.getPosition());

                    for (Marker mMarker : listOfMarkers) {
                           listOfPoints.add(mMarker.getPosition());
                    }
                    listOfMarkers.add(marker);

                    marker.showInfoWindow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }
        });
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        try {
            FileOutputStream output = this.getContext().openFileOutput("latlngpoints5.txt",
                    Context.MODE_PRIVATE);
            output.write(("").getBytes());
            DataOutputStream dout = new DataOutputStream(output);
            dout.writeInt(listOfPoints.size()); // Save line count
            for (LatLng point : listOfPoints) {
                dout.writeUTF(point.latitude + "," + point.longitude);
            }
            dout.flush(); // Flush stream ...
            dout.close(); // ... and close.
            listOfPoints.clear();
            listOfMarkers.clear();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return true;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    public void SuppressionMarqueur(){


        for(Marker marker : listOfMarkers ){
            marker.remove();
        }
        listOfMarkers.clear();
        listOfPoints.clear();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
