import AdverseEventForm from './components/AdverseEventForm';

function App() {
  return (
    <div className="min-h-screen bg-gray-100">
      <nav className="bg-blue-600 p-4 text-white shadow-lg">
        <div className="container mx-auto">
          <h1 className="text-xl font-bold">SafeMeds PV Portal</h1>
        </div>
      </nav>

      <div className="container mx-auto p-4">
        <AdverseEventForm />
      </div>
    </div>
  );
}

export default App;
