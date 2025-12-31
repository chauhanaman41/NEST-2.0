import { useState } from 'react';
import api from '../api/axios';

const AdverseEventForm = () => {
    const [formData, setFormData] = useState({
        drugName: '',
        description: '',
        patientDetails: ''
    });
    const [status, setStatus] = useState({ type: '', message: '' });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Validation: Description length < 20
        if (formData.description.length < 20) {
            setStatus({ type: 'error', message: 'Description must be at least 20 characters.' });
            return;
        }

        try {
            const response = await api.post('/events', {
                ...formData,
                reporterId: 'PORTAL-USER', // Mock reporter ID
                timestamp: new Date().toISOString()
            });

            setStatus({
                type: 'success',
                message: `Report Submitted Successfully! Report ID: ${response.data.id}`
            });
            // Reset form
            setFormData({ drugName: '', description: '', patientDetails: '' });
        } catch (error) {
            console.error(error);
            setStatus({ type: 'error', message: 'Failed to submit report. Please try again.' });
        }
    };

    return (
        <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded-lg shadow-md">
            <h2 className="text-2xl font-bold mb-6 text-gray-800">Report Adverse Event</h2>

            {status.message && (
                <div className={`p-4 mb-4 rounded ${status.type === 'error' ? 'bg-red-100 text-red-700' : 'bg-green-100 text-green-700'}`}>
                    {status.message}
                </div>
            )}

            <form onSubmit={handleSubmit}>
                <div className="mb-4">
                    <label className="block text-gray-700 text-sm font-bold mb-2">Drug Name</label>
                    <input
                        type="text"
                        name="drugName"
                        value={formData.drugName}
                        onChange={handleChange}
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        required
                    />
                </div>

                <div className="mb-4">
                    <label className="block text-gray-700 text-sm font-bold mb-2">Patient Details</label>
                    <input
                        type="text"
                        name="patientDetails"
                        value={formData.patientDetails}
                        onChange={handleChange}
                        placeholder="e.g., Age 45, Male"
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        required
                    />
                </div>

                <div className="mb-6">
                    <label className="block text-gray-700 text-sm font-bold mb-2">Description</label>
                    <textarea
                        name="description"
                        value={formData.description}
                        onChange={handleChange}
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline h-32"
                        placeholder="Describe the reaction (min 20 chars)..."
                        required
                    />
                </div>

                <div className="flex items-center justify-between">
                    <button
                        type="submit"
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full"
                    >
                        Submit Report
                    </button>
                </div>
            </form>
        </div>
    );
};

export default AdverseEventForm;
